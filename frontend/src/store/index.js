import { createStore } from 'vuex';

export default createStore({
    state:{
        login: false,
    },
    getters: {
        isLogin(state){
            return state.login;
        },
    },
    mutations: {
        setLogin(state, value) {
            state.login = value;
        }
    },
    actions: {
        setLogin: ({commit}, value) => {
            commit('setLogin', value);
        },
    }
})