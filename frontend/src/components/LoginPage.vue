<template>
  <div class="container-fluid d-flex justify-content-center align-items-center min-vh-100 bg-light-green">
    <div class="card shadow-lg" style="width: 100%; max-width: 400px; border-radius: 20px;">
      <div class="card-body">
        <h2 class="card-title text-center mb-4 text-success fw-bold">Woori BAS</h2>
        <p class="text-center text-muted mb-4">Please login to your account</p>
        <form @submit.prevent="handleLogin">
          <div class="mb-3">
            <label for="userid" class="form-label fw-semibold">아이디</label>
            <input
                type="text"
                v-model="username"
                id="userid"
                class="form-control shadow-sm"
                placeholder="아이디 입력"
                required
            />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label fw-semibold">비밀번호</label>
            <input
                type="password"
                v-model="password"
                id="password"
                class="form-control shadow-sm"
                placeholder="비밀번호 입력"
                required
            />
          </div>
          <div v-if="errorMessage" class="text-danger small mb-3">
            {{ errorMessage }}
          </div>
          <div class="form-check mb-3">
            <input type="checkbox" id="rememberMe" class="form-check-input" />
            <label class="form-check-label" for="rememberMe">아이디 저장</label>
          </div>
          <button type="submit" class="btn btn-success w-100 py-2 shadow">로그인</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";


export default {
  name: "UserLogin",
  data() {
    return {
      username: "",
      password: "",
      errorMessage: null,
    };
  },
  methods: {
    async handleLogin() {
      try {
        // 서버로 로그인 요청
        const response = await axios.post("http://localhost:8080/api/login", {
          username: this.username,
          password: this.password,
        });

        alert("로그인 성공");
        // 서버 응답에서 JWT를 가져와 쿠키에 저장
        const jwtToken = response.headers['jwt-token']; // 서버에서 JWT 토큰을 헤더로 받았다고 가정
        document.cookie = `jwt=${jwtToken}; path=/;`;

        // 로그인한 사용자 이름을 Vuex에 저장
        this.$store.dispatch('setUsername', this.username);

        this.$router.push('/'); // 홈 페이지로 리다이렉트
      } catch (error) {
        this.errorMessage = "아이디 또는 비밀번호가 잘못되었습니다.";
        console.error(error);
      }
    }
  }
};
</script>

<style scoped>
.bg-light-green {
  background-color: #f1f8f5;
}

.card {
  border: none;
  border-radius: 20px;
  background-color: #ffffff;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 1.5rem;
  color: #28a745;
  font-weight: bold;
}

.text-muted {
  font-size: 0.9rem;
}

.form-control {
  border-radius: 10px;
  padding: 0.75rem;
  border: 1px solid #ced4da;
}

.form-control:focus {
  border-color: #28a745;
  box-shadow: 0 0 5px rgba(40, 167, 69, 0.5);
}

.btn-success {
  background-color: #28a745;
  border: none;
  border-radius: 10px;
  font-weight: bold;
}

.btn-success:hover {
  background-color: #218838;
}

.container-fluid {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 0;
}
</style>
