const MAX_FILE_SIZE = 25 * 1024 * 1024; // 25 MB

function validateFiles() {
    const file1 = document.getElementById("file1").files[0];
    const file2 = document.getElementById("file2").files[0];

    if (!file1 || !file2) {
        showError("Selecione dois arquivos PDF.");
        return false;
    }

    if (file1.size > MAX_FILE_SIZE || file2.size > MAX_FILE_SIZE) {
        showError("Cada arquivo deve ter no máximo 25 MB.");
        return false;
    }

    return true;
}

function showError(message) {
    const container = document.getElementById("errorContainer");
    const messageBox = document.getElementById("errorMessage");

    messageBox.textContent = message;
    container.removeAttribute("hidden");
}
