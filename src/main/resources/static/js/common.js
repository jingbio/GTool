
    /**
    * 显示消息提示
    * @param {string} message 消息文本
    * @param {number} duration 显示时间（毫秒）
    */
    function msg(message, duration = 3000) {
        // 创建消息元素
        const msgDiv = document.createElement('div');
        msgDiv.className = 'custom-msg';
        msgDiv.textContent = message;

        // 将消息添加到 body 中
        document.body.appendChild(msgDiv);

        // 如果 duration 不为 0，则设置定时器移除消息
        if (duration > 0) {
            setTimeout(() => {
                msgDiv.remove();
            }, duration);
        }else {
            // 添加点击事件监听器到 document
            const handleDocumentClick = function(event) {
                // 检查点击是否在消息元素之外
                if (!msgDiv.contains(event.target)) {
                    msgDiv.remove();
                    document.removeEventListener('click', handleDocumentClick);
                }
            };
            document.addEventListener('click', handleDocumentClick);
        }
    }

