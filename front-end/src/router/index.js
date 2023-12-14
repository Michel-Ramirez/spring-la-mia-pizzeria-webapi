import { createRouter, createWebHistory } from "vue-router";
import HomePage from "../pages/HomePage.vue";
import DetailePage from "../pages/DatailPage.vue";
import PizzaCreate from "../pages/PizzaCreatePage.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", name: "home", component: HomePage },
    { path: "/pizza/:id", name: "detail", component: DetailePage },
    { path: "/pizza/create", name: "create", component: PizzaCreate },
  ],
});
export { router };
