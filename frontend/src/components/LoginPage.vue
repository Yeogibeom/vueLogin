<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="handleLogin">
      <input type="text" v-model="username" placeholder="Username" required />
      <input type="password" v-model="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('/api/login', {
          username: this.username,
          password: this.password
        }, {
          headers: {
            'Content-Type': 'application/json'  // 명시적으로 Content-Type 설정
          }
        });
        console.log('Login successful:', response.data);
        // 예: 토큰 저장 (JWT 방식 사용 시)
        localStorage.setItem('token', response.data.token);
      } catch (error) {
        console.error('Login failed:', error.response?.data || error.message);
        alert('Login failed. Please check your username and password.');
      }
    }
  }
};
</script>
