const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
const tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	return new bootstrap.Tooltip(tooltipTriggerEl)
});

const myModal = document.getElementById('myModal');
const bsModal = new bootstrap.Modal(document.getElementById('myModal'));

myModal.addEventListener('show.bs.modal', function (event) {
	const button = event.relatedTarget;
	const id = button.getAttribute('data-bs-id');

	const modalTitle = myModal.querySelector('.modal-title');
	const modalButton = myModal.querySelector('.modal-footer #delete');

	modalTitle.textContent = 'Exclusão da Atividade ' + id;
  modalButton.addEventListener('click', function(){
		deleteActivity(button,id);
		bsModal.hide();
	});
});


function deleteActivity(button, id){
	const row = button.parentNode.parentNode.parentNode; // button->span->td->tr
	const url = "activityRegister?action=remove&activity-id="+id;

	// Solicitação GET.
	fetch(url)
	// Tratamento do sucesso
	.then(response =>{
		return response.json(); // converter para JSON
	})
	.then(data =>{
	   	if(data){
			row.parentNode.removeChild(row); // remover linha da tabela
		}
	})
	.catch(error => console.log('Erro de solicitação', error)); // lidar com os erros por catch
}