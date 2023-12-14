<script setup>
import { ref } from 'vue';
import axios from 'axios';


const newPizza = ref({
    name: "Salsiccia e patate",
    price: 9.99,
    photo: "",
    description: "Le patate le patate"
})
const sendData = async () => {
    const data = await axios.post('http://127.0.0.1:8080/api/v1.0/pizzas/pizza/create', newPizza.value)
    this.$router.push('/');
}
</script>

<template>
    <div class="wrapper">
        <h1 class="my-5">Create a new Pizza</h1>

        <form @submit.prevent="sendData">
            <div class="row">
                <div class="col">
                    <div class="mb-3">
                        <label for="pizzaName" class="form-label">Name</label>
                        <input type="text" class="form-control" id="pizzaName" name="name" v-model="newPizza.name">
                    </div>
                </div>
                <div class="col">
                    <div class="mb-3">
                        <label for="pizzaPice" class="form-label">Price</label>
                        <input type="numb" class="form-control" id="pizzaPice" name="price" v-model="newPizza.price">
                    </div>
                </div>
            </div>
            <div class="mb-3">
                <label for="pizzaPice" class="form-label">Image</label>
                <input type="url" class="form-control" id="pizzaPice" name="photo" v-model="newPizza.photo">
            </div>
            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">Description</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="description"
                    v-model="newPizza.description"></textarea>
            </div>
            <div class="text-center mt-5">
                <button type="submit" class="btn btn-success me-3">Save</button>
                <button type="reset" class="btn btn-warning">Reset</button>
            </div>
        </form>
    </div>
</template>

<style scoped>
.wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 50px;
}

form {
    width: 600px;
    border: 1px solid gray;
    border-radius: 10px;
    padding: 50px
}
</style>