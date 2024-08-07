<template>
    <div>
        <el-container class="input-container">
            <header></header>
            <article-write-editor></article-write-editor>
        </el-container>
    </div>
</template>

<script>
import ArticleWriteEditor from '@/components/ArticleWriteEditor.vue';

export default {
  components: {ArticleWriteEditor},
    
}
</script>

<style>
.input-container {
  display: flex;
  justify-content: center; /* 或者其他需要的布局 */
}

</style>