<template>
  <div>
    <div class="container">
      <h2>Post</h2>

      <div class="form-group">
        <label for="title">제목</label>
        <input type="text" v-model="title" id="title" placeholder="제목을 입력하세요" readonly />
      </div>
      <div class="form-group">
        <label for="content">글내용</label>
        <div id="editor" class="editor-readonly"></div>
      </div>

    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { Editor } from '@toast-ui/editor';
import { useRoute } from 'vue-router';

export default {
  name: 'WritePage',
  setup() {
    const title = ref('');
    const content = ref('');
    const route = useRoute();
    let editorInstance = null;

    onMounted(async () => {
      // Toast UI Editor 초기화 (편집 불가능 상태)
      editorInstance = new Editor({
        el: document.querySelector('#editor'),
        height: '400px',
        initialEditType: 'wysiwyg',
        previewStyle: 'vertical',
        toolbarItems: [],
        disableEditing: true,  // 초기부터 편집 불가능
      });

      const id = route.params.id;

      // 서버에서 데이터 가져오기
      try {
        const response = await axios.get(`http://localhost:8080/api/post/${id}`);
        const post = response.data;
        title.value = post.title;
        content.value = post.content;

        // 데이터를 가져온 후 편집기 내용 설정
        editorInstance.setMarkdown(post.content);
      } catch (error) {
        console.error('게시물을 가져오는 데 오류가 발생했습니다:', error);
        alert('게시물을 가져오는 데 오류가 발생했습니다.');
      }
    });

    return {
      title,
      content,
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
  background-color: #f8f8f8;
  pointer-events: none;
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

/* 편집기 스타일, 읽기 전용으로 표시 */
.editor-readonly {
  background-color: #f8f8f8;
  border: 1px solid #ccc;
  pointer-events: none;
}
</style>
