<template>
  <b-card
      :title="item.title"
      :img-src="item.imageUrl"
      img-alt="Image"
      img-top
      img-height="150px"
      tag="article"
      class="mb-2"
  >

    <b-card-text>{{ item.createdAt }}</b-card-text>
    <div>
      <b-icon-heart/>{{item.heartNum}}
    </div>
  </b-card>
</template>

<script>

import {BIconHeart} from "bootstrap-icons-vue";

export default {
  name: "Post",
  components: {
    BIconHeart
  },
  props: {
    item: Object
  }
}
</script>

<style scoped>

</style>