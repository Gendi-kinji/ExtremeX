package com.example.extremex

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.extremex.data.ExtremeXDatabase
import com.example.extremex.data.OfflineRepository
import com.example.extremex.data.User
import com.example.extremex.DestinationViewModelFactory
import com.example.extremex.DestinationViewModel

class Signup : AppCompatActivity() {

    // Repository and ViewModel setup
    private lateinit var repository: OfflineRepository
    private lateinit var viewModel: DestinationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        // Initialize database and repository
        val database = ExtremeXDatabase.getInstance(applicationContext)
        repository = OfflineRepository(database.destinationDao(), database.userDao())

        // Initialize ViewModel with Factory
        val viewModelFactory = DestinationViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DestinationViewModel::class.java)

        // Handle window insets for proper padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.signupButton)
        button.setOnClickListener {
            handleSignup()
        }
    }

    private fun handleSignup() {
        // Retrieve user inputs
        val username = findViewById<EditText>(R.id.signupusernameInput).text.toString().trim()
        val email = findViewById<EditText>(R.id.signupemail).text.toString().trim()
        val password = findViewById<EditText>(R.id.signuppasswordInput).text.toString()
        val confirmPassword = findViewById<EditText>(R.id.confirmpasswordInput).text.toString()

        // Input validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a User object
        val user = User(username = username, email = email, password = password)

        // Insert user via ViewModel
        viewModel.insertUser(user)

        // Show success message
        Toast.makeText(this, "User created successfully!", Toast.LENGTH_SHORT).show()

        // Close activity
        finish()
    }
}
