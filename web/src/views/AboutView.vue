<template>
  <div class="about">
    <h1>This is my first vue case</h1>
    <myHeader></myHeader>
    <myList></myList>
    <MyFooter></MyFooter>
  </div>
</template>

<script>
import MyHeader from "./demo01/MyHeader.vue";
import MyList from "./demo01/MyList.vue";
import MyFooter from "./demo01/MyFooter.vue";
export default {
  components: { MyHeader, MyList, MyFooter },
  name: "AboutView",
};
</script>

<style scoped>
</style>
