import { createStore } from 'vuex';  // vuex에서 createStore를 가져옵니다.

export default createStore({
    state: {
        username: null,  // 사용자 이름 상태
    },
    mutations: {
        setUsername(state, username) {
            state.username = username;
        },
        clearUsername(state) {
            state.username = null;
        },
    },
    actions: {
        setUsername({ commit }, username) {
            commit('setUsername', username);
        },
        clearUsername({ commit }) {
            commit('clearUsername');
        },
    },
    getters: {
        getUsername(state) {
            return state.username;
        },
    },
});