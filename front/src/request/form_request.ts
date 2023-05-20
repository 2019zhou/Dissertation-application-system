import axios from "axios";
import { message } from "ant-design-vue";


const forminstance = axios.create({
    timeout: 15000,
    baseURL: "http://1.15.174.76:8080/",
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function (data) {
        let ret = ''
        for (let it in data) {
            ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
        }
        return ret
    }]
})


forminstance.interceptors.request.use(
    (config) => {
        return config;
    },
    (err) => {
        return Promise.reject(err);
    }
);

// 相应拦截器
forminstance.interceptors.response.use(
    (result) => {
        const data = result.data;
        if (data.errno === 1) {
            message.error(data.errmsg || "网络请求错误");
        }
        return data;
    },
    (err) => {
        return Promise.reject(err);
    }
);


export default forminstance;