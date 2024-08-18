import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:7000/',
    timeout: 10000,
    withCredentials: true,
});

export default instance;