<script setup>
import { onMounted, popScopeId, ref } from 'vue';
import axios from 'axios';

const pizzas = ref(null);
const termSerched = ref("");
const getPizzas = async () => {

    if (termSerched.value !== "") {
        const data = await axios.get(`http://127.0.0.1:8080/api/v1.0/pizzas?query=${termSerched.value}`);
        pizzas.value = data.data;
    } else {
        const data = await axios.get(`http://127.0.0.1:8080/api/v1.0/pizzas`);
        pizzas.value = data.data;
    }
}

const deletePizza = async (id) => {
    const data = await axios.delete(`http://127.0.0.1:8080/api/v1.0/pizzas/pizza/delete/${id}`)
    await getPizzas();
}

onMounted(getPizzas);
</script>

<template>
    <h1 class="my-5">Menù</h1>

    <div class="d-flex justify-content-end ">
        <form class="d-flex" role="search" @submit.prevent="getPizzas(termSerched)">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" v-model="termSerched">
            <button class="btn btn-outline-success me-3" type="submit">Search</button>
        </form>
        <router-link :to="{ name: 'create' }" class="btn btn-primary">Add pizza</router-link>
    </div>



    <!-- <div v-if="pizzas.length === 0" class="mt-5 text-center ">
        <h2>We're sorry but we don't have any pizzas</h2>
    </div> -->



    <table class="table table-striped mt-5 ">
        <thead class="table-primary">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">NAME</th>
                <th scope="col">DESCRIPTION</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="pizza in  pizzas " :key="pizza.id">
                <th scope="row">{{ pizza.id }}</th>
                <td>{{ pizza.name }}</td>
                <td>{{ pizza.description }}</td>
                <td class="d-flex justify-content-end ">
                    <router-link :to="{ name: 'detail', params: { id: pizza.id } }"
                        class="btn btn-sm btn-info">View</router-link>
                    <router-link to="" class="btn btn-sm btn-warning mx-3">edit</router-link>
                    <button class="btn btn-sm btn-danger" @click="deletePizza(pizza.id)">delte</button>
                </td>
            </tr>
        </tbody>
    </table>
</template>
