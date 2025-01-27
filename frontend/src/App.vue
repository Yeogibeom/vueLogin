<template>
  <div>

    <AddNavbar />
    <router-view />
  </div>
</template>

<script>
// import { mapGetters, mapActions } from 'vuex';

import AddNavbar from './components/AddNavbar.vue';

export default {
  components: {
    AddNavbar
  },
  // computed: {
  //   ...mapGetters(['getUsername']),  // Vuex에서 username을 가져옴
  // },
  // methods: {
  //   ...mapActions(['setUser']),  // Vuex 액션으로 사용자 설정
  //   getCookie(name) {
  //     const value = `; ${document.cookie}`;
  //     const parts = value.split(`; ${name}=`);
  //     if (parts.length === 2) return parts.pop().split(';').shift();
  //     return null;
  //   }
  // },
  // mounted() {
  //   const jwt = this.getCookie('jwt');
  //   if (jwt) {
  //     const decoded = jwtDecode(jwt);
  //     this.setUser(decoded.username);  // Vuex에 사용자 이름 설정
  //   }
  // }
};
</script>
