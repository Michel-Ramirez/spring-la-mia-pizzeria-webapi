<script>
</script>

<template>
    <h1 class="my-5">Create a new Pizza</h1>

    <form action="">
        <div class="row">
            <div class="col">
                <div class="mb-3">
                    <label for="pizzaName" class="form-label">Name</label>
                    <input type="text" class="form-control" id="pizzaName">
                </div>
            </div>
            <div class="col">
                <div class="mb-3">
                    <label for="pizzaPice" class="form-label">Price</label>
                    <input type="numb" class="form-control" id="pizzaPice">
                </div>
            </div>
        </div>
        <div class="mb-3">
            <label for="pizzaPice" class="form-label">Image</label>
            <input type="url" class="form-control" id="pizzaPice">
        </div>
        <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
    </form>
</template>