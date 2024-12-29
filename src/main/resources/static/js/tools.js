// 页面加载完成时执行
// window.onload = function () {
//     //启用欢迎
//     msg('欢迎~~', 1000);
// }

//监听工具栏按钮事件
document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.route-button');
    //const textarea = document.getElementById('in');
    // 设置第一个按钮为默认选中状态
    if (buttons.length > 0) {
        buttons[0].classList.add('selected');
        //textarea.value = '';
    }
    buttons.forEach(button => {
        button.addEventListener('click', function() {
            // 移除所有按钮的选中状态
            buttons.forEach(btn => btn.classList.remove('selected'));
            // 添加当前按钮的选中状态
            this.classList.add('selected');
            //textarea.value = '';
        });
    });
});

function route(route){
    let formData = {
        route: route.value
    };
    $.ajax({
        type: 'POST',
        url: '/tools/route',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            if (response.code === 200) {
                updateOps(response.data);
            } else {
                msg(response.msg, 0)
            }
        },
        error: function(error) {
            msg(error.msg, 0)
        }
    });
}

function updateOps(ops){
    const container = document.getElementById('ops');

    // 清除现有的所有按钮
    while (container.firstChild) {
        container.removeChild(container.firstChild);
    }

    // 添加新的按钮
    ops.forEach(op => {
        const button = document.createElement('button');
        button.textContent = op.name;
        button.value = op.op;
        button.onclick = function() { execute(this); };
        container.appendChild(button);
    });
}


function execute (op){
    const textarea = document.getElementById('in');
    const route = document.querySelector('.selected');
    let formData = {
        route: route.value,
        op:op.value,
        data: textarea.value
    };
    $.ajax({
        type: 'POST',
        url: '/tools/execute',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            if (response.code === 200) {
                msg(response.msg, 3000);
                textarea.value=response.data;
            } else {
                msg(response.msg, 0)
            }
        },
        error: function(error) {
            msg(error.msg, 0)
        }
    });
}

