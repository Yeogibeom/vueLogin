<template>
  <div class="container-fluid d-flex justify-content-center align-items-center min-vh-100 bg-light-green">
    <div class="card shadow-lg" style="width: 100%; max-width: 400px; border-radius: 20px;">
      <div class="card-body">
        <h2 class="card-title text-center mb-4 text-success fw-bold">Register</h2>
        <p class="text-center text-muted mb-4">Create your account</p>
        <form @submit.prevent="handleRegister">
          <div class="mb-3">
            <label for="username" class="form-label fw-semibold">제품명</label>
            <input
                type="text"
                v-model="title"
                id="title"
                class="form-control shadow-sm"
                placeholder="Enter your username"
                required
            />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label fw-semibold">가격</label>
            <input
                v-model="price
"
                id="price"
                class="form-control shadow-sm"
                placeholder="Enter your password"
                required
            />
          </div>

          <button type="submit" class="btn btn-success w-100 py-2 shadow">추가</button>
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
      title: '',
      price: '',

    };
  },
  methods: {
    async handleRegister() {
      try {
        const response = await axios.post('http://localhost:8080/api/edit', {
          title: this.title,
          price: this.price,
        });
        console.log('Registration successful:', response.data);
        alert(response.data);
        this.$router.push('/list'); // 성공 시 로그인 페이지로 리디렉션
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
