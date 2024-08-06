<template>
    <div>
        <my-nav></my-nav>
        <el-container>
            <el-header></el-header>
            <lmessage-editor></lmessage-editor>
            <lmessage-list></lmessage-list>
        </el-container>
    </div>
</template>

<script>
import LmessageEditor from './LmessageEditor.vue'
import LmessageList from './LmessageList.vue'
export default {
  components: { LmessageEditor, LmessageList },
    
}
</script>

<style>

</style>