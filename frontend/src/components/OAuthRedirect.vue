<template lang="ko">
</template>

<script>
export default {
  name: "OAuthRedirect",
  created() {
    localStorage.setItem("at",this.$route.query.at);
    localStorage.setItem("rt",this.$route.query.rt);
    this.$router.push("/");
  },
  data() {
    return {
      token:''
    }
  }
}
</script>

<style scoped>

</style>