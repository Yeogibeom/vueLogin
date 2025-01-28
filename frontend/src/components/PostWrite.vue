<template>
  <div>

    <div class="container">
      <h2>Write</h2>

      <div v-if="username">
        <p>{{ username }}님!</p>
      </div>
      <div class="form-group">
        <label for="title">제목</label>
        <input type="text" v-model="title" id="title" placeholder="제목을 입력하세요" />
      </div>
      <div class="form-group">
        <label for="content">글쓰기</label>
        <div id="editor"></div>
      </div>
      <div class="button-group">
        <button @click="saveDraft">임시저장</button>
        <button @click="send">send</button>
      </div>
    </div>
  </div>
</template>

<script>
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/editor';
import {computed, onMounted, ref} from 'vue';
import axios from 'axios';
import { mapGetters } from 'vuex';
import store from "@/store";

export default {

  name: 'WritePage',
  computed: {
    ...mapGetters(['getUsername']),  // vuex에서 username을 가져옵니다.
    username() {
      return this.getUsername;
    }
  },
  setup() {
    const title = ref('');
    const content = ref('');
    const createdAt = ref('');// 작성된 내용을 저장할 변수
    const username = computed(() => store.getters.getUsername);
    let editorInstance = null;
    // Toast UI Editor 초기화
    onMounted(() => {
      editorInstance = new Editor({
        el: document.querySelector('#editor'),
        height: '400px',
        initialEditType: 'wysiwyg',
        previewStyle: 'vertical',
        toolbarItems: [
          ['heading', 'bold', 'italic', 'strike'],
          ['ul', 'ol', 'task'],
          ['table', 'link'],
          ['code', 'codeblock'],
        ],
      });
    });

    // 임시저장
    const saveDraft = () => {
      content.value = editorInstance.getMarkdown(); // Markdown 내용 저장
      console.log('Draft saved:', { title: title.value, content: content.value , createdAt: createdAt.value});
    };

    // 서버로 데이터 전송
// 서버로 데이터 전송
    const send = async () => {
      content.value = editorInstance.getMarkdown(); // Markdown 내용 저장
      createdAt.value = new Date().toISOString();

      console.log('Title:', title.value);
      console.log('Content:', content.value);
      console.log('Created At:', createdAt.value);
      console.log('사용자아이디:', username.value);    // Vuex에서 받아온 username 확인

      try {
        const response = await axios.post('http://localhost:8080/api/write', {
          title: title.value,
          content: content.value,
          createdAt: createdAt.value,
          username: username.value
        });

        if (response.status === 200) {
          console.log('Data sent successfully:', response.data);
          alert('전송이 완료되었습니다!');
        } else {
          console.error('Failed to send data:', response.status);
          alert('전송에 실패했습니다.');
        }
      } catch (error) {
        console.error('Error while sending data:', error);
        alert('서버와 통신 중 오류가 발생했습니다.');
      }
    };
    return {
      title,
      content,
      saveDraft,
      send,
    };
  },
};
</script>

<style scoped>

.header h1 {
  margin: 0;
}
.header a {
  text-decoration: none;
  color: white;
  background-color: #003d55;
  padding: 5px 10px;
  border-radius: 5px;
}
.container {
  margin: 20px auto;
  width: 90%;
  max-width: 800px;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 20px;
}
.container h2 {
  margin-top: 0;
}
.form-group {
  margin-bottom: 20px;
}
.form-group label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}
.form-group input[type='text'] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.button-group {
  display: flex;
  justify-content: flex-end;
}
.button-group button {
  margin-left: 10px;
  padding: 10px 20px;
  background-color: #004d66;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>