<template lang="ko">
</template>

<script>
export default {
  name: "OAuthRedirect",
  created() {
    const token = this.$route.query.token;
    console.log(token);
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