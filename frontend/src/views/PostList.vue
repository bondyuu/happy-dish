<template>
  <div class="search-form">
    <b-nav-form>
      <b-form-input class="mr-sm-2" placeholder="Search" v-model="searchTitle"></b-form-input>
      <b-button variant="outline-success" class="my-2 my-sm-0" @:click="getPosts(this.searchTitle,0)">Search</b-button>
      <b-button variant="outline-success" class="bt-write" href="/write-post">New Post</b-button>
    </b-nav-form>
  </div>
  <div class="post-list" ref="scrollcheck">
    <div class="wrapper" v-for="post in list" :key="post">
      <Post :item="post"/>
    </div>
  </div>
</template>

<script>
import {getPosts} from "@/api";
import Post from "@/components/Post.vue";

export default {
  name: "PostList",
  components: {Post},
  data() {
    return {
      list: [],
      searchTitle: '',
      page: 0,
      isLast: '',
    }
  },
  mounted() {
    this.getPosts('', 0);
  },
  methods: {
    getPosts(title, page) {
      getPosts(title, page)
          .then((res) => {
            console.log(res);
            this.list = res.data.content;
            if (res.data.last === false) {
              this.page++;
            } else {
              this.isLast = true;
            }
          })
          .catch((err) => {
            console.log(err);
          });
    }
  }
}
</script>

<style scoped>
.post-list{
  text-align: center;
}
.wrapper {
  margin-bottom: 20px;
  margin-right: 3%;
  width: 32%;
  display: inline-block;
}
.mr-sm-2 {
  width: 30%;
  margin-left: 15%;
  margin-right: 2%;
  margin-bottom: 3%;
}
.my-sm-0 {
  height: 36px;
  margin-right: 24%;
}
.bt-write {
  height: 36px;
}
</style>