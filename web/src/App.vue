<template>
  <div id="app">
    <HeaderView/>
    <ListView></ListView>
    <FooterView/>
  </div>
</template>

<script>
import HeaderView from "@/components/HeaderView";
import ListView from "@/components/ListView";
import FooterView from "@/components/FooterView";

export default {
  name: 'App',
  components: {
    FooterView,
    ListView,
    HeaderView,
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
