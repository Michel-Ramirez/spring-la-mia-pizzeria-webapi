<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router';

//INIZIALIZZO L'OGGETTO VUOTO (NON A NULL ALTRIMENTI AL CARICAMENTO QUANDO NON CI SONO ANCORA I DATI IL HTML SI ROMPER PERCHE' NON TROVA pizza.name)
const pizza = ref("");

//RICHIAMO L'OGGETTO CON I DATI DELLA ROUTE
const route = useRoute();

const fetchData = async () => {

    //ESTRAGGO DALL'OGGETTO ROUTE IL ID PER POI PASSARLO ALLA CHIAMATA AXIOS
    const pizzaId = route.params.id

    const data = await axios.get(`http://127.0.0.1:8080/api/v1.0/pizzas/${pizzaId}`);
    pizza.value = data.data;

    console.log(pizza.value)
}

//AL MOUNT CHIAMO LA FUNZIONE
onMounted(fetchData);
</script>

<template>
    <div class="d-flex align-items-center justify-content-between ">
        <h1 class="my-5">{{ pizza.name }}</h1>
        <span class="fs-3 fw-bold "><i>{{ pizza.price }} €</i></span>
    </div>

    <div class="row">
        <div class="col-8">
            <p>{{ pizza.description }}</p>
        </div>
        <div class="col-4">
            <img :src="pizza.photo" :alt="pizza.name" class="img-fluid">
        </div>
    </div>
</template>