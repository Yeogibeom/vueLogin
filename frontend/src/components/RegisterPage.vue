<template>
  <div>
    <h2>Register</h2>
    <form @submit.prevent="handleRegister">
      <input type="text" v-model="username" placeholder="Username" required />
      <input type="password" v-model="password" placeholder="Password" required />
      <input type="text" v-model="displayName" placeholder="Display Name" required />
      <button type="submit">Register</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
      displayName: ''
    };
  },
  methods: {
    async handleRegister() {
      try {
        const response = await axios.post('http://localhost:8080/api/register', {
          username: this.username,
          password: this.password,
          displayName: this.displayName
        });
        console.log('Registration successful:', response.data);
        // 이후 로직 추가 (예: 성공 시 로그인 페이지로 리디렉션)
      } catch (error) {
        console.error('Registration failed:', error.response.data);
      }
    }
  }
};
</script>
