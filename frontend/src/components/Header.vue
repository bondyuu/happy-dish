<template>
  <header class="header-container">
    <div class="logo">
      <h2>HAPDI</h2>
    </div>
    <div class="nav-container">
      <router-link to="">게시글</router-link> |
      <router-link to="">게시글</router-link> |
      <router-link to="">게시글</router-link> |
      <router-link to="">게시글</router-link>
    </div>
    <div class="login">
      <router-link to="/login"><b-button variant="outline-primary">Login</b-button></router-link>
      <router-link to="/sign-up"><b-button variant="outline-primary">Sign-Up</b-button></router-link>
    </div>
  </header>
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>
.header-container {
  display: flex;
  margin-top: 20px;
  margin-left: 4%;
  width: 92%;
}
.nav-container {
  margin-left: 3%;
  margin-right: 60%;
}
</style>