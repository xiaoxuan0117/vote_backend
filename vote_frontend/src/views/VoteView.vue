<template>
  <div class="vote">
    <div class="content-wrapper">
      <div class="content">
        <div class="title">Vote</div>
        <div class="user-info">
          <div class="user-name">username: <span>{{ user.name }}</span></div>
          <div class="logout">
            <button v-on:click="logout">登出</button>
          </div>
        </div>
        <div class="item-list">
          <ItemComponent v-for="item in items" v-bind:key="item.id" :item="item" :hasVote="user.hasVote" v-on:removeRecord="removeRecord" v-on:addRecord="addRecord"></ItemComponent>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ItemComponent from '@/components/Item.vue';
export default {
  name: 'VoteView',
  components: {
    ItemComponent
  },
  data() {
    return {
      user: {
        name: '',
        hasVote: []
      },
      items: {}
    }
  },
  methods: {
    async getItemsVotes(){
      try{
        const items = {};
        const votedItems = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/votes`).then(res => res.json());
        const allItems = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/item`).then(res => res.json());
        votedItems.forEach(item => {
          items[`${item.id}`] = {id: item.id, name: item.name, voteCount: item.voteCount};
        });
        allItems.forEach(item => {
          if(!items[item.id]) {
            items[item.id] = {id: item.id, name: item.name, voteCount: 0};
          }
        });
        this.items = Object.values(items);
      } catch(err) {
        console.log(err);
      }
    },
    async userDetail(){
      try{
        const data = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/user/${this.user.name}`).then(res => res.json());
        this.user.hasVote = data;
      }catch(err){
        console.log(err);
      }
    },
    async removeRecord({itemId}){
      try{
        const data = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/record/${this.user.name}/${itemId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if(data.status === 200) {
          alert('移除成功');
        } else {
          alert('移除失敗');
        }
        this.getItemsVotes();
        this.userDetail();
      }catch(err){
        console.log(err);
      }
    },
    async addRecord({itemId}){
      try{
        const data = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/record`, {
          method: 'POST',
          body: JSON.stringify({
            name: this.user.name,
            itemId: itemId
          }),
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if(data.status === 200) {
          alert('投票成功');
        } else {
          alert('投票失敗');
        }
        this.getItemsVotes();
        this.userDetail();
      }catch(err){
        console.log(err);
      }
    },
    logout() {
      localStorage.removeItem('username');
      this.$router.push('/');
    }
  },
  created() {
    this.user.name = localStorage.getItem('username');
    this.userDetail();
  },
  mounted(){
    this.getItemsVotes();
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
  }
}
.title {
  font-size: 36px;
  font-weight: 700;
  color: #416586;
  margin-bottom: 10px;
}

.user-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin-bottom: 20px;
  margin-top: 40px;

  .user-name {
    font-size: 20px;
    font-weight: 700;
    color: #416586;
    margin-right: 20px;
  }

  .logout {

    & button {
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
  }
}

.item-list {
  position: relative;
  width: 100%;
}
</style>
