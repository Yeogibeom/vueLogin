<template>
  <div class="item-list">
    <div v-for="item in items" :key="item.id" class="card">
      <img src="https://placehold.co/300" alt="상품 이미지" class="card-image" />
      <div class="card-content">
        <h4 class="item-title">{{ item.title }}</h4>
        <router-link :to="{ name: 'Detail', params: { id: item.id } }" class="card-link">상세페이지</router-link>
        <router-link :to="{ name: 'UpdateItem', params: { id: item.id } }" class="card-link">수정</router-link>
        <button  > </button>
        <p class="item-price">{{ item.price }} 원</p>
      </div>
    </div>
  </div>
</template>2

<script>
import axios from 'axios';
export default {
  data() {
    return {
      items: [],
    };
  },
  mounted() {
    this.fetchItems();
  },
  methods: {
    async fetchItems() {
      try {
        const response = await axios.get('http://localhost:8080/api/list');
        this.items = response.data;
      } catch (error) {
        console.error('아이템 데이터를 불러오는 데 실패했습니다.', error);
      }
    },
  },
};
</script>

<style scoped>
.item-list {
  display: flex;
  flex-wrap: wrap;
  gap: 16px; /* 카드 간격 */
  justify-content: center;
  padding: 16px;
}

.card {
  background-color: #f8f9fa;
  border: 1px solid #ddd;
  border-radius: 8px;
  width: 300px;
  padding: 16px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.card-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
  margin-bottom: 12px;
}

.card-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 8px;
}

.item-title {
  font-size: 20px;
  font-weight: bold;
  margin: 0;
}

.card-link {
  font-size: 14px;
  color: #007bff;
  text-decoration: none;
  margin: 4px 0;
}

.card-link:hover {
  text-decoration: underline;
}

.item-price {
  font-size: 16px;
  color: #28a745;
  margin: 8px 0 0;
}
</style>
