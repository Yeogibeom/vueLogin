<template>
  <div>
    <!-- 네비게이션 바 -->
    <div class="navbar">
      <!-- 여기 네비게이션 컴포넌트를 포함하거나 작성하세요 -->
    </div>

    <!-- 상세 페이지 -->
    <div class="detail">
      <h4 class="page-title">상세페이지</h4>
      <div class="card">
        <img src="https://placehold.co/300" alt="상품 이미지" class="detail-image" />
        <div class="card-content">

          <p class="item-title">제품명: {{ item.title }}</p>
          <p class="item-price">가격: {{ item.price }} 원</p>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      item: {
        id: null,
        title: '',
        price: '',
      },
      comments: [],
      newComment: '',
    };
  },
  async mounted() {
    await this.fetchItemDetails();
  },
  methods: {
    async fetchItemDetails() {
      try {
        const id = this.$route.params.id;  // id를 URL 파라미터로부터 받아옵니다.

        const response = await axios.get(`http://localhost:8080/api/item/${id}`); // id를 포함한 URL로 요청
        this.item = response.data;
      } catch (error) {
        console.error('상품 정보를 가져오는 데 실패했습니다.', error);
      }
    },
  },
};
</script>
<style scoped>
/* 상세 페이지 카드 스타일 */
.detail-page {
  text-align: center;
  padding: 40px;
}

.page-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

.card {
  background-color: #f8f9fa;
  border: 1px solid #ddd;
  border-radius: 8px;
  width: 350px;
  margin: 0 auto;
  padding: 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.detail-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
  margin-bottom: 20px;
}

.card-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
}

.item-id,
.item-title,
.item-price {
  font-size: 18px;
  margin: 0;
}

.item-title {
  font-weight: bold;
  color: #333;
}

.item-price {
  font-size: 20px;
  color: #28a745;
}
</style>