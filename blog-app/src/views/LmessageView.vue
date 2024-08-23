<template>
    <div>
        <my-nav></my-nav>
        <br>
        <el-container>
            <el-header></el-header>
            <lmessage-editor></lmessage-editor>
            <lmessage-list></lmessage-list>
        </el-container>
    </div>
</template>

<script>
import LmessageEditor from '../components/LmessageEditor.vue'
import LmessageList from '../components/LmessageList.vue'
export default {
  components: { LmessageEditor, LmessageList },
    
}
</script>

<style>

</style>