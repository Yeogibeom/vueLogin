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
            <a  class="nav-link active"  href="#">사용자이름</a>
          </li>
          <li class="nav-item">
            <button class="btn btn-link nav-link" @click="handleLogout">로그아웃</button>
          </li>
        </ul>

      </div>
    </div>
  </nav>
</template>

<script>
// import { mapGetters } from 'vuex';
import axios from "axios";

export default {
  // computed: {
  //   ...mapGetters(['getUsername']),  // Vuex에서 username 가져오기
  // },
  methods: {

    async handleLogout() {
      try {
        // 서버에 로그아웃 요청 전송
        await axios.post('http://localhost:8080/logout');

        // 쿠키 삭제
        document.cookie = 'jwt=; Max-Age=-1; path=/;';
        this.clearUser(); // Vuex에서 사용자 이름 초기화
        this.$router.push('/'); // 홈 페이지로 리다이렉트
        alert('로그아웃 되었습니다.');
      } catch (error) {
        console.error('로그아웃 요청 실패:', error);
        alert('로그아웃 요청 중 오류가 발생했습니다.');
      }
    }

  }
};
</script>
