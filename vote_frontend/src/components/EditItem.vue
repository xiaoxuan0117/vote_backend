<template>
  <div class="item-wrapper">
    <div class="item">
      <div class="info">
        <div class="name">{{item.name}}</div>
      </div>
      <div class="function">
        <div class="updateItem">
          <input type="text" v-model="updateName" placeholder="請輸入更新名稱">
          <button v-on:click="updateItem">更新項目</button>
        </div>
        <div class="removeItem">
          <button v-on:click="removeItem">移除項目</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EditItemComponent',
  props: {
    item: {
      type: Object,
      required: true
    },
  },
  data() {
    return {
      updateName: '',
    }
  },
  methods: {
    removeItem(){
      this.$emit('removeItem', {itemId: this.item.id});
    },
    updateItem(){
      this.$emit('updateItem', {newName: this.updateName, itemId: this.item.id});
    }
  }
}
</script>

<style scoped lang="scss">
.item-wrapper {
  width: 100%;
  border-radius: 5px;
  box-shadow: 0px 0px 4px rgba(0, 0, 0, 0.2);
  margin: 20px 0;
  padding: 20px;
  box-sizing: border-box;
}

.item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.info {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 10px;

  & .name {
    font-size: 20px;
    font-weight: 500;
    color: #416586;
    margin-right: 20px;
  }
}

.function {
  display: flex;
  flex-direction: column;
}

.updateItem {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 10px;
  margin-bottom: 10px;
}

input {
  height: 30px;
  border-radius: 5px;
  border: 1px solid #D9D9D9;
  padding-left: 5px;
  outline: none;

  &:focus {
    border: 2px solid #b6b6b6;
  }
}

button {
    width: 67px;
    height: 30px;
    color:#416586;
    background-color: #fff;
    border-radius: 5px;
    border: none;
    box-shadow: 0px 0px 4px rgba(0, 0, 0, 0.2);
    margin-bottom: 2px;

    &:hover{
      cursor: pointer;
    box-shadow: 0.5px 0.5px 4px rgba(0, 0, 0, 0.2);
    }
  }
</style>