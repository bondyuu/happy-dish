import axios from 'axios';
import qs from 'qs';
function createInstance() { // 정말 기본 URL만 셋팅
    return axios.create({
        baseURL: "http://localhost:8080",
    });
}

const instance = createInstance();

export function signUp(data) {
    return instance.post("/users/save", data)
}
export function formLogin(data) {
    return instance.post("/login", qs.stringify(data), {headers:{"Content-Type": "application/x-www-form-urlencoded"}})
}