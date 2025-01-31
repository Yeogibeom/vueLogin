<template>
  <nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">WooriBAS</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link" to="/">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/login">Login</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/register">회원가입</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link active" to="/list">김</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link active" to="/edit">상품추가</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link active" to="/PostList">게시물</router-link>
          </li>

          <li class="nav-item" v-if="username">
            <span class="nav-link">{{ username }}</span>
          </li>

          <li class="nav-item">
            <button class="btn btn-link nav-link" @click="handleLogout">로그아웃</button>
          </li>
          <li class="nav-item">
            <router-link class="nav-link active" to="/PostWrite">게시물 추가하기</router-link>
          </li>

        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import axios from "axios";
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['getUsername']), //
    username() {
      return this.getUsername;
    }
  },
  methods: {
    async handleLogout() {
      try {
        // 서버에 로그아웃 요청 전송
        await axios.post('http://localhost:8080/logout');

        // 쿠키 삭제
        document.cookie = 'jwt=; Max-Age=-1; path=/;';
        this.$store.dispatch('clearUsername');
        this.$router.push('/');
        alert('로그아웃 되었습니다.');
      } catch (error) {
        console.error('로그아웃 요청 실패:', error);
        alert('로그아웃 요청 중 오류가 발생했습니다.');
      }
    }
  }
};
</script>
