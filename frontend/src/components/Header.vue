<template>
  <header class="header-container">
    <div class="logo" @click="main">
      <h2>HAPDI</h2>
    </div>
    <nav class="nav-container">
      <router-link to="/posts">게시글</router-link> |
      <router-link to="/asdfasdf">게시글</router-link> |
      <router-link to="/asdf">게시글</router-link> |
      <router-link to="/my-page">마이페이지</router-link>
    </nav>
    <div class="btn-container" v-if="this.$store.getters.isLogin">
      <b-button variant="outline-primary" @click="logout">Logout</b-button>
    </div>
    <div class="btn-container" v-else>
      <router-link to="/login"><b-button variant="outline-primary" style="margin-right: 2%;">Login</b-button></router-link>
      <router-link to="/sign-up"><b-button variant="outline-primary">Sign-Up</b-button></router-link>
    </div>
  </header>
</template>

<script>
export default {
  name: "Header",
  methods: {
    main() {
      this.$router.push('/');
    },
    logout() {
      localStorage.removeItem('at');
      localStorage.removeItem('rt');
      this.$store.commit('setLogin',false);
      this.$router.push("/");
    }
  }
}
</script>

<style scoped>
.header-container {
  display: flex;
  margin-top: 35px;
  margin-left: 4%;
  width: 92%;
}
.nav-container {
  margin-left: 3%;
  margin-right: 35%;
}
nav a {
  font-weight: bold;
  color: #2c3e50;
  font-size: 25px;
  margin-right: 20px;
  margin-left: 20px;
}

nav a.router-link-exact-active {
  color: #42b983;
}

a {
  text-decoration: none;
}

.btn-container {
  margin-top: 10px;
  width: 15%;
}
</style>