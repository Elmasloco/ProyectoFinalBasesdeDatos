
    const rolSelect = document.getElementById("rol");
    const contrasenaCampo = document.getElementById("campo-contraseña");
    contrasenaCampo.style.display="none";
    rolSelect.addEventListener("change", function() {
        
        if (rolSelect.value === "admin") {
            contrasenaCampo.style.display = "block";
        } else {
            contrasenaCampo.style.display = "none";
        }
    });

    
