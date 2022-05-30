<template>
  <div>
    <ui>
      <li
        v-for="person in persons"
        :key="person.id"
      >
        {{ person.name }}
      </li>
    </ui>
  </div>
</template>

<script>
export default {
  name: "TwoVue",
  data() {
    return {
      persons: [
        { id: 1, name: "wls1" },
        { id: 2, name: "wls2" },
        { id: 3, name: "wls3" },
      ],
    };
  },
};
</script>

<style scoped>
</style>