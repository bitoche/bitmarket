document.addEventListener('DOMContentLoaded', function() {
    const hamburgerMenu = document.getElementById('hamburger-menu');
    const dropdownMenu = document.getElementById('dropdown-menu');

    hamburgerMenu.addEventListener('click', function() {
        if (dropdownMenu.style.display === 'none' || dropdownMenu.style.display === '') {
            dropdownMenu.style.display = 'flex';
            hamburgerMenu.style.backgroundColor = 'gray';
        } else {
            dropdownMenu.style.display = 'none';
            hamburgerMenu.style.backgroundColor = '';
        }
    });

    // Close the dropdown menu if clicked outside of it
    window.addEventListener('click', function(event) {
        if (!hamburgerMenu.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.style.display = 'none';
            hamburgerMenu.style.backgroundColor = '';
        }
    });
});