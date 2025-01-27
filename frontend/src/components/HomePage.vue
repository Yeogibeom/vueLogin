<template>
  <div class="container-fluid d-flex justify-content-center align-items-center min-vh-100 bg-light-green">
    <div class="text-center">

      <p v-if="username" class="fw-semibold text-success">{{ username }}!</p> <!-- Vuex에서 username 사용 -->

    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';  // Vuex getter 사용

export default {
  computed: {
    ...mapGetters(['getUsername']),  // Vuex에서 username을 가져옴
  },
  methods: {
    goToLogin() {
      this.$router.push('/login');
    },
    goToRegister() {
      this.$router.push('/register');
    }
  }
};
</script>

<style scoped>
.bg-light-green {
  background-color: #f1f8f5;
}

h1 {
  font-size: 2.5rem;
}

p {
  font-size: 1.1rem;
}

.btn {
  padding: 0.75rem 2rem;
  border-radius: 10px;
  font-weight: bold;
}

.btn-primary {
  background-color: #007bff;
  border: none;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.btn-success {
  background-color: #28a745;
  border: none;
}

.btn-success:hover {
  background-color: #218838;
}
</style>
