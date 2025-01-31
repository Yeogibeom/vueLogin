// import { createStore } from 'vuex';  // vuex에서 createStore를 가져옵니다.
//
// export default createStore({
//     state: {
//         username: null,  // 사용자 이름 상태
//     },
//     mutations: {
//         setUsername(state, username) {
//             state.username = username;
//         },
//         clearUsername(state) {
//             state.username = null;
//         },
//     },
//     actions: {
//         setUsername({ commit }, username) {
//             commit('setUsername', username);
//         },
//         clearUsername({ commit }) {
//             commit('clearUsername');
//         },
//     },
//     getters: {
//         getUsername(state) {
//             return state.username;
//         },
//     },
// });

import { createStore } from 'vuex';

const store = createStore({
    state: {
        jwt: null, // JWT 토큰 저장
        username: null, // 사용자 이름 저장
    },
    mutations: {
        setJwt(state, jwt) {
            state.jwt = jwt; // JWT 토큰 저장
        },
        setUsername(state, username) {
            state.username = username; // 사용자 이름 저장
        },
        clearAuth(state) {
            state.jwt = null; // 로그아웃 시 JWT 초기화
            state.username = null; // 사용자 이름 초기화
        },
    },
    actions: {
        setJwt({ commit }, jwt) {
            commit('setJwt', jwt);
        },
        setUsername({ commit }, username) {
            commit('setUsername', username);
        },
        clearAuth({ commit }) {
            commit('clearAuth');
        },
    },
    getters: {
        isAuthenticated(state) {
            return !!state.jwt; // JWT가 존재하면 인증된 상태로 간주
        },
        getJwt(state) {
            return state.jwt; // JWT 가져오기
        },
        getUsername(state) {
             return state.username;
       },
    },
});

export default store;
