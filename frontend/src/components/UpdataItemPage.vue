<template>
  <div class="container-fluid d-flex justify-content-center align-items-center min-vh-100 bg-light-green">
    <div class="card shadow-lg" style="width: 100%; max-width: 400px; border-radius: 20px;">
      <div class="card-body">
        <h2 class="card-title text-center mb-4 text-success fw-bold">Register</h2>
        <p class="text-center text-muted mb-4">Create your account</p>
        <form @submit.prevent="handleRegister">
          <div class="mb-3">

            <label for="username" class="form-label fw-semibold">id</label>
            <input
                type="text"
                v-model="id"
                id="id"
                class="form-control shadow-sm"
                :placeholder="item?.id"
                required

            />

            <label for="username" class="form-label fw-semibold">제품명</label>
            <input
                type="text"
                v-model="title"
                id="title"
                class="form-control shadow-sm"
                :placeholder="item?.title"
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
                :placeholder="item?.price"
                required

            />
          </div>

          <button type="submit" class="btn btn-success w-100 py-2 shadow">수정</button>
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
      id:this.$route.params.id,
      title: '',
      price: '',

    };
  },
  async mounted(){
    await this.fetchItemUpdate();
  },
  methods: {
    async fetchItemUpdate(){
       try {
         const id =this.$route.params.id;
         console.log(this.$route.params.id);
         const items = await axios.get(`http://localhost:8080/api/update/${id}`);
         this.item=items.data;
         this.title = this.item.title;
         this.price = this.item.price;

       } catch (error) {
         console.error('상품 정보를 가져오는 데 실패했습니다.', error);
       }
    },

    async handleRegister() {
      try {

        const response = await axios.post(`http://localhost:8080/api/updateitem`, {
          id:this.id,
          title: this.title,
          price: this.price
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
