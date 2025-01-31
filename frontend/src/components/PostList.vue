<template>
  <div>
    <div class="container">
      <!-- 검색 바 -->
      <div class="search-container">
        <select v-model="searchType">
          <option value="title">제목</option>
          <option value="content">내용</option>
          <option value="username">작성자</option>
        </select>
        <input type="text" v-model="searchKeyword" placeholder="검색어 입력" />
        <button @click="searchPosts">검색</button>
      </div>

      <!-- 게시판 테이블 -->
      <table>
        <thead>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일시</th>
          <th>조회수</th>
          <th>댓글수</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(post) in posts" :key="post.id">
          <td>{{ post.id }}</td>
          <td>
            <router-link :to="{ name: 'DetailPost', params: { id: post.id } }">
              {{ post.title }}
            </router-link>
          </td>
          <td>{{ post.username }}</td>
          <td>{{ post.createdAt }}</td>
          <td>{{ post.views }}</td>
          <td>{{ post.comments }}</td>
          <td>
            <button @click="deletePost(post.id)">삭제</button>
          </td>
        </tr>
        </tbody>
      </table>

      <!-- 데이터 로딩 중 표시 -->
      <div v-if="loading" class="loading">
        데이터를 불러오는 중입니다...
      </div>

      <!-- 에러 메시지 표시 -->
      <div v-if="errorMessage" class="error">
        {{ errorMessage }}
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";

export default {
  name: "SearchComponent",
  setup() {
    const searchType = ref("title"); // 기본값: 제목 검색
    const searchKeyword = ref("");
    const posts = ref([]);
    const loading = ref(false);
    const errorMessage = ref(null);

    // 게시글 목록 가져오기
    const fetchPosts = async () => {
      loading.value = true;
      errorMessage.value = null;
      try {
        const response = await axios.get("http://localhost:8080/api/post"); // Spring Boot API 호출
        posts.value = response.data;
      } catch (error) {
        console.error("Error fetching posts:", error);
        errorMessage.value = "게시글 데이터를 불러오는 데 실패했습니다.";
      } finally {
        loading.value = false;
      }
    };

    // 검색 버튼 클릭 시 호출
    const searchPosts = async () => {
      loading.value = true;
      try {
        const response = await axios.get("http://localhost:8080/api/search", {
          params: { type: searchType.value, keyword: searchKeyword.value },
        });
        posts.value = response.data;
      } catch (error) {
        console.error("검색 오류:", error);
        errorMessage.value = "검색 중 오류가 발생했습니다.";
      } finally {
        loading.value = false;
      }
    };

    // 게시글 삭제
    const deletePost = async (id) => {
      if (confirm("정말 삭제하시겠습니까?")) {
        try {
          await axios.delete(`http://localhost:8080/api/post/${id}`);
          posts.value = posts.value.filter((post) => post.id !== id);
        } catch (error) {
          console.error("삭제 오류:", error);
          alert("게시글 삭제 중 오류가 발생했습니다.");
        }
      }
    };

    // 컴포넌트가 마운트되면 게시글 목록을 가져옴
    onMounted(fetchPosts);

    return {
      searchType,
      searchKeyword,
      searchPosts,
      fetchPosts,
      deletePost,
      posts,
      loading,
      errorMessage,
    };
  },
};
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f9f9f9;
}

.container {
  margin: 20px auto;
  width: 90%;
  max-width: 1200px;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 20px;
}

.search-container {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

.search-container input[type="text"] {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search-container button {
  padding: 10px 15px;
  background-color: #004d66;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

table {
  width: 100%;
  border-collapse: collapse;
}

table th,
table td {
  padding: 10px;
  border: 1px solid #ddd;
  text-align: center;
}

table th {
  background-color: #f2f2f2;
}

.loading,
.error {
  text-align: center;
  margin-top: 10px;
}

td a {
  text-decoration: none;
  color: inherit;
}
</style>
