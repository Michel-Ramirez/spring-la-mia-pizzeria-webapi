<script>
import axios from 'axios';
export default {
    data() {
        return {
            pizza: []
        }
    },
    methods: {
        fetchData() {
            const pizzaId = this.$route.params.id
            axios.get(`http://127.0.0.1:8080/api/v1.0/pizzas/${pizzaId}`).then(res => {
                this.pizza = res.data
            })
        }
    },
    mounted() {
        this.fetchData()
    }
}


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