import axios from 'axios';
import store from '../store'; // Vuex 스토어 임포트

// Axios 인스턴스 생성
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080', // API 기본 URL
    timeout: 5000, // 요청 타임아웃 설정
});

// 요청 인터셉터 설정
axiosInstance.interceptors.request.use(
    (config) => {
        const token = store.getters.getJwt; // Vuex에서 JWT 가져오기
        if (token) {
            config.headers.Authorization = `Bearer ${token}`; // Authorization 헤더 추가
        }
        return config;
    },
    (error) => Promise.reject(error)
);

// 응답 인터셉터 설정 (선택 사항)
axiosInstance.interceptors.response.use(
    (response) => response,
    (error) => Promise.reject(error)
);

export default axiosInstance;
