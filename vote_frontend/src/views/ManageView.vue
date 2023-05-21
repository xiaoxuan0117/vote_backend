<template>
  <div class="vote">
    <div class="content-wrapper">
      <div class="content">
        <div class="title">Manage</div>
        <button v-on:click="home">回到首頁</button>
        <div class="addItem">
          <input type="text" v-model="newItemName" placeholder="請輸入項目名稱">
          <button v-on:click="addItem">新增項目</button>
        </div>
        <div class="item-list">
          <EditItemComponent v-for="item in items" v-bind:key="item.id" :item="item" v-on:removeItem="removeItem" v-on:updateItem="updateItem"></EditItemComponent>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import EditItemComponent from '@/components/EditItem.vue';
import { validateInput } from  '@/utils/index.js';
export default {
  name: 'VoteView',
  components: {
    EditItemComponent
  },
  data() {
    return {
      newItemName: '',
      items: []
    }
  },
  methods: {
    home(){
      this.$router.push('/');
    },
    async getItems(){
      try{
        const data = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/item`).then(res => res.json());
        this.items = data;
      } catch(err) {
        console.log(err);
      }
    },
    async removeItem({itemId}){
      if(!validateInput(itemId, 'number')) return alert('請選擇正確的項目編號');
      try{
        const data = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/item/${itemId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          },
        });
        if(data.status === 200) {
          alert('移除成功');
        } else {
          alert('移除失敗');
        }
        this.getItems();
      } catch(err) {
        console.log(err);
      }
    },
    async addItem(){
      if(!validateInput(this.newItemName, 'string')) return alert('請輸入正確格式');
      try{
        const data = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/item`, {
          method: 'POST',
          body: JSON.stringify({
            name: this.newItemName,
          }),
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if(data.status === 200) {
          alert('新增成功');
        } else {
          alert('新增失敗');
        }
        this.getItems();
      }catch(err){
        console.log(err);
      }
    },
    async updateItem({newName, itemId}){
      if(!validateInput(newName, 'string') || !validateInput(itemId, 'number')) return alert('請輸入正確格式');
      try{
        const data = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/item`, {
          method: 'PATCH',
          body: JSON.stringify({
            newName: newName,
            itemId: itemId
          }),
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if(data.status === 200) {
          alert('更新成功');
        } else {
          alert('更新失敗');
        }
        this.getItems();
      }catch(err){
        console.log(err);
      }
    },
  },
  mounted() {
    this.getItems();
  }
}

</script>

<style scoped lang="scss">
.vote {
  padding-top: 100px;
}

.content-wrapper {
  display: flex;
  justify-content: center;
  position: relative;
  width: 100%;

  & .content {
    width: 40%;
    text-align: start;

    @media screen and (max-width: 768px) {
    width: 100%;
    }
  }
}
.title {
  font-size: 36px;
  font-weight: 700;
  color: #416586;
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

.addItem {
  display: flex;
  align-items: center;
  gap: 10px;
  width: 100%;
  margin-bottom: 20px;
  margin-top: 40px;
}

.item-list {
  position: relative;
  width: 100%;
}
</style>
