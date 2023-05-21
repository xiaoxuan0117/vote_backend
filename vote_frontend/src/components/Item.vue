<template>
  <div class="item-wrapper">
    <div class="item">
      <div class="info">
        <div class="name">{{item.name}}</div>
        <div class="votes">累積票數: <span>{{ item.voteCount }}</span></div>
      </div>
      <div class="vote">
        <div class="has-vote" v-if="checkoutVote(item.id)">
          <img src="../assets/voted.svg" alt="voted" />
          <button v-on:click="removeVote">移除投票</button>
        </div>
        <div class="has-no-vote" v-if="!checkoutVote(item.id)">
          <button v-on:click="addVote">投票</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ItemComponent',
  props: {
    item: {
      type: Object,
      required: true
    },
    hasVote: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
    }
  },
  methods: {
    checkoutVote(itemId){
      return this.hasVote.includes(itemId) > 0;
    },
    removeVote(){
      this.$emit('removeRecord', {itemId: this.item.id});
    },
    addVote(){
      this.$emit('addRecord', {itemId: this.item.id});
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
  flex-flow: wrap;
  flex-direction: row;
  align-items: center;
  gap: 10px;

  & .name {
    font-size: 20px;
    font-weight: 500;
    color: #416586;
    margin-right: 20px;
  }

  & .votes {
    font-size: 14px;
    font-weight: 500;
    color: #6d6d6d;
  }
}

.vote {

  & .has-vote {
    display: flex;
    flex-direction: row;
    align-items: center;
  }

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
</style>