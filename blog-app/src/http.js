import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://47.121.211.207/api/',
    timeout: 10000,
    withCredentials: true,
});

export default instance;