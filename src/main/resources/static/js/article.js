// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/articles');
            });
    });
}
/* HTML에서 id를 delete-btn로 설정한 엘리먼트를 찾아 그 엘리먼트에서 클릭 이벤트가 발생하면
fetch() 메서드를 통해 /api/articles/DELETE 요청을 보내는 역할을 함
fetch() 메서드에 이어지는 then() 메서드는 fetch()가 잘 완료되면 연이어 실행되는 메서드
alert() 메서드는 then() 메서드가 실행되는 시점에 웹 브라우저 화면으로 삭제가 완료됨을 알리는 팝업을 띄워주는 메서드
location.replace()메서드는 실행 시 사용자의 웹 브라우저 화면을 현재 주소를 기반해 옮겨주는 역할을 함 */

// 수정 기능
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/articles/${id}`);
            });
    });
}

// 생성 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener('click', event => {
        fetch('/api/articles', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('등록 완료되었습니다.');
                location.replace('/articles');
            });
    });
}