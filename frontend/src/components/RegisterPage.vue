<template>
  <div class="container-fluid d-flex justify-content-center align-items-center min-vh-100 bg-light-green">
    <div class="card shadow-lg" style="width: 100%; max-width: 400px; border-radius: 20px;">
      <div class="card-body">
        <h2 class="card-title text-center mb-4 text-success fw-bold">Register</h2>
        <p class="text-center text-muted mb-4">Create your account</p>
        <form @submit.prevent="handleRegister">
          <div class="mb-3">
            <label for="username" class="form-label fw-semibold">Username</label>
            <input
                type="text"
                v-model="username"
                id="username"
                class="form-control shadow-sm"
                placeholder="Enter your username"
                required
            />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label fw-semibold">Password</label>
            <input
                type="password"
                v-model="password"
                id="password"
                class="form-control shadow-sm"
                placeholder="Enter your password"
                required
            />
          </div>
          <div class="mb-3">
            <label for="displayName" class="form-label fw-semibold">Display Name</label>
            <input
                type="text"
                v-model="displayName"
                id="displayName"
                class="form-control shadow-sm"
                placeholder="Enter your display name"
                required
            />
          </div>
          <button type="submit" class="btn btn-success w-100 py-2 shadow">Register</button>
        </form>
      </div>
    </div>
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
        alert('Registration successful! Redirecting to login...');
        this.$router.push('/login'); // 성공 시 로그인 페이지로 리디렉션
      } catch (error) {
        console.error('Registration failed:', error.response?.data || error.message);
        alert('Registration failed. Please try again.');
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
  padding: 0;
}
</style>
