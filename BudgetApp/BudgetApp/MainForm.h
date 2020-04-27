#pragma once
#include <fstream>
#include <sstream>
#include <string>  
#include <iostream>  
#include <msclr\marshal_cppstd.h>
#include <regex>
#include <ctime>


namespace BudgetApp {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for MainForm
	/// </summary>
	public ref class MainForm : public System::Windows::Forms::Form
	{
	public:
		MainForm(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
			std::time_t t = std::time(0);   // get time now
			std::tm* now = std::localtime(&t);

			System::String^ currentTime = now->tm_mon + 1 + "/" + now->tm_mday;
			System::String^ cmm = currentTime->Substring(0, currentTime->IndexOf('/'));
			System::String^ cdd = currentTime->Substring(currentTime->IndexOf('/') + 1);

			cm = System::Convert::ToInt16(cmm);
			cd = System::Convert::ToInt16(cdd);
			currentTime = currentTime +"/"+( now->tm_year + 1900);

			cy = System::Convert::ToInt16(now->tm_year + 1900);
			curTimeLabel->Text = currentTime;
			getBudgets();
		}

		void getBudgets();
		void getBudgetHistory();
		void updateBudgetHistory();
		double trackSpending();
		void getCurrentBallance();
		int getDaysBetween(int m1, int d1, int y1, int m2, int d2, int y2);
		void getWeeklyBudget();
		void getBudgetStatus();
		void getDates();
		bool checkForDouble(System::String^ number);


	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~MainForm()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::ComboBox^  budgetDropdown;
	private: System::Windows::Forms::TextBox^  newBudgetText;
	private: System::Windows::Forms::Button^  newBudgetButton;
	private: System::Windows::Forms::Panel^  panel1;
	private: System::Windows::Forms::Button^  openButton;

	private: System::Windows::Forms::Label^  budgetLabel;
	private: System::Windows::Forms::DataGridView^  purchaseGrid;




	private: System::Windows::Forms::Button^  updateButton;
	private: System::Windows::Forms::Label^  label5;

	private: System::Windows::Forms::Label^  curBalanceLabel;
	private: System::Windows::Forms::Label^  curBallance;
	private: System::Windows::Forms::TextBox^  balanceText;
	private: System::Windows::Forms::Label^  label2;
	private: System::Windows::Forms::Label^  label1;
	private: System::Windows::Forms::Label^  origBalanceLabel;

	private: System::Windows::Forms::Label^  label3;
	private: System::Windows::Forms::Label^  label4;








	private: System::Windows::Forms::Label^  label6;
	private: System::Windows::Forms::Label^  label7;
	private: System::Windows::Forms::Label^  label8;
	private: System::Windows::Forms::Label^  weeklyBudgetLabel;

			 double globalBalance;
			 double globalCurBalance;
			 int m1, m2, d1, d2, cm, cy, cd, y1, y2;





	private: System::Windows::Forms::Label^  label11;
	private: System::Windows::Forms::Label^  statusText;
	private: System::Windows::Forms::Label^  label12;
	private: System::Windows::Forms::Label^  endDateLabel;

	private: System::Windows::Forms::Label^  startDateLabel;

	private: System::Windows::Forms::Label^  label15;
	private: System::Windows::Forms::Label^  curTimeLabel;
	private: System::Windows::Forms::Label^  label9;
	private: System::Windows::Forms::NumericUpDown^  d2updown;

	private: System::Windows::Forms::NumericUpDown^  d1updown;
private: System::Windows::Forms::NumericUpDown^  m2updown;


private: System::Windows::Forms::NumericUpDown^  m1updown;
private: System::Windows::Forms::DataGridViewTextBoxColumn^  purchasedItem;
private: System::Windows::Forms::DataGridViewTextBoxColumn^  itemPrice;
private: System::Windows::Forms::NumericUpDown^  y2updown;
private: System::Windows::Forms::NumericUpDown^  y1updown;
private: System::Windows::Forms::Label^  label13;
private: System::Windows::Forms::Label^  label10;








	protected:

	protected:

	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			System::Windows::Forms::DataGridViewCellStyle^  dataGridViewCellStyle5 = (gcnew System::Windows::Forms::DataGridViewCellStyle());
			System::Windows::Forms::DataGridViewCellStyle^  dataGridViewCellStyle7 = (gcnew System::Windows::Forms::DataGridViewCellStyle());
			System::Windows::Forms::DataGridViewCellStyle^  dataGridViewCellStyle8 = (gcnew System::Windows::Forms::DataGridViewCellStyle());
			System::Windows::Forms::DataGridViewCellStyle^  dataGridViewCellStyle6 = (gcnew System::Windows::Forms::DataGridViewCellStyle());
			this->budgetDropdown = (gcnew System::Windows::Forms::ComboBox());
			this->newBudgetText = (gcnew System::Windows::Forms::TextBox());
			this->newBudgetButton = (gcnew System::Windows::Forms::Button());
			this->panel1 = (gcnew System::Windows::Forms::Panel());
			this->y2updown = (gcnew System::Windows::Forms::NumericUpDown());
			this->y1updown = (gcnew System::Windows::Forms::NumericUpDown());
			this->label13 = (gcnew System::Windows::Forms::Label());
			this->label10 = (gcnew System::Windows::Forms::Label());
			this->d2updown = (gcnew System::Windows::Forms::NumericUpDown());
			this->d1updown = (gcnew System::Windows::Forms::NumericUpDown());
			this->m2updown = (gcnew System::Windows::Forms::NumericUpDown());
			this->m1updown = (gcnew System::Windows::Forms::NumericUpDown());
			this->label9 = (gcnew System::Windows::Forms::Label());
			this->balanceText = (gcnew System::Windows::Forms::TextBox());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->openButton = (gcnew System::Windows::Forms::Button());
			this->label6 = (gcnew System::Windows::Forms::Label());
			this->label4 = (gcnew System::Windows::Forms::Label());
			this->label7 = (gcnew System::Windows::Forms::Label());
			this->label3 = (gcnew System::Windows::Forms::Label());
			this->budgetLabel = (gcnew System::Windows::Forms::Label());
			this->purchaseGrid = (gcnew System::Windows::Forms::DataGridView());
			this->purchasedItem = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->itemPrice = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->updateButton = (gcnew System::Windows::Forms::Button());
			this->label5 = (gcnew System::Windows::Forms::Label());
			this->curBalanceLabel = (gcnew System::Windows::Forms::Label());
			this->curBallance = (gcnew System::Windows::Forms::Label());
			this->origBalanceLabel = (gcnew System::Windows::Forms::Label());
			this->label8 = (gcnew System::Windows::Forms::Label());
			this->weeklyBudgetLabel = (gcnew System::Windows::Forms::Label());
			this->label11 = (gcnew System::Windows::Forms::Label());
			this->statusText = (gcnew System::Windows::Forms::Label());
			this->label12 = (gcnew System::Windows::Forms::Label());
			this->endDateLabel = (gcnew System::Windows::Forms::Label());
			this->startDateLabel = (gcnew System::Windows::Forms::Label());
			this->label15 = (gcnew System::Windows::Forms::Label());
			this->curTimeLabel = (gcnew System::Windows::Forms::Label());
			this->panel1->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->y2updown))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->y1updown))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->d2updown))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->d1updown))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->m2updown))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->m1updown))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->purchaseGrid))->BeginInit();
			this->SuspendLayout();
			// 
			// budgetDropdown
			// 
			this->budgetDropdown->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->budgetDropdown->FormattingEnabled = true;
			this->budgetDropdown->Location = System::Drawing::Point(18, 16);
			this->budgetDropdown->Name = L"budgetDropdown";
			this->budgetDropdown->Size = System::Drawing::Size(182, 28);
			this->budgetDropdown->TabIndex = 0;
			this->budgetDropdown->Text = L"Select Budget";
			// 
			// newBudgetText
			// 
			this->newBudgetText->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->newBudgetText->Location = System::Drawing::Point(64, 196);
			this->newBudgetText->Name = L"newBudgetText";
			this->newBudgetText->Size = System::Drawing::Size(108, 26);
			this->newBudgetText->TabIndex = 1;
			// 
			// newBudgetButton
			// 
			this->newBudgetButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->newBudgetButton->Location = System::Drawing::Point(79, 391);
			this->newBudgetButton->Name = L"newBudgetButton";
			this->newBudgetButton->Size = System::Drawing::Size(108, 40);
			this->newBudgetButton->TabIndex = 2;
			this->newBudgetButton->Text = L"Add";
			this->newBudgetButton->UseVisualStyleBackColor = true;
			this->newBudgetButton->Click += gcnew System::EventHandler(this, &MainForm::newBudgetButton_Click);
			// 
			// panel1
			// 
			this->panel1->BackColor = System::Drawing::SystemColors::ControlDarkDark;
			this->panel1->Controls->Add(this->y2updown);
			this->panel1->Controls->Add(this->y1updown);
			this->panel1->Controls->Add(this->label13);
			this->panel1->Controls->Add(this->label10);
			this->panel1->Controls->Add(this->d2updown);
			this->panel1->Controls->Add(this->d1updown);
			this->panel1->Controls->Add(this->m2updown);
			this->panel1->Controls->Add(this->m1updown);
			this->panel1->Controls->Add(this->label9);
			this->panel1->Controls->Add(this->balanceText);
			this->panel1->Controls->Add(this->label2);
			this->panel1->Controls->Add(this->label1);
			this->panel1->Controls->Add(this->openButton);
			this->panel1->Controls->Add(this->budgetDropdown);
			this->panel1->Controls->Add(this->newBudgetButton);
			this->panel1->Controls->Add(this->newBudgetText);
			this->panel1->Controls->Add(this->label6);
			this->panel1->Controls->Add(this->label4);
			this->panel1->Controls->Add(this->label7);
			this->panel1->Controls->Add(this->label3);
			this->panel1->Location = System::Drawing::Point(-6, -5);
			this->panel1->Name = L"panel1";
			this->panel1->Size = System::Drawing::Size(279, 535);
			this->panel1->TabIndex = 3;
			// 
			// y2updown
			// 
			this->y2updown->Location = System::Drawing::Point(225, 337);
			this->y2updown->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 99, 0, 0, 0 });
			this->y2updown->Name = L"y2updown";
			this->y2updown->Size = System::Drawing::Size(38, 20);
			this->y2updown->TabIndex = 31;
			// 
			// y1updown
			// 
			this->y1updown->Location = System::Drawing::Point(225, 294);
			this->y1updown->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 99, 0, 0, 0 });
			this->y1updown->Name = L"y1updown";
			this->y1updown->Size = System::Drawing::Size(38, 20);
			this->y1updown->TabIndex = 30;
			// 
			// label13
			// 
			this->label13->AutoSize = true;
			this->label13->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label13->Location = System::Drawing::Point(211, 334);
			this->label13->Name = L"label13";
			this->label13->Size = System::Drawing::Size(15, 24);
			this->label13->TabIndex = 29;
			this->label13->Text = L"/";
			// 
			// label10
			// 
			this->label10->AutoSize = true;
			this->label10->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label10->Location = System::Drawing::Point(211, 290);
			this->label10->Name = L"label10";
			this->label10->Size = System::Drawing::Size(15, 24);
			this->label10->TabIndex = 28;
			this->label10->Text = L"/";
			// 
			// d2updown
			// 
			this->d2updown->Location = System::Drawing::Point(167, 337);
			this->d2updown->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 31, 0, 0, 0 });
			this->d2updown->Name = L"d2updown";
			this->d2updown->Size = System::Drawing::Size(38, 20);
			this->d2updown->TabIndex = 27;
			// 
			// d1updown
			// 
			this->d1updown->Location = System::Drawing::Point(162, 293);
			this->d1updown->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 31, 0, 0, 0 });
			this->d1updown->Name = L"d1updown";
			this->d1updown->Size = System::Drawing::Size(38, 20);
			this->d1updown->TabIndex = 26;
			// 
			// m2updown
			// 
			this->m2updown->Location = System::Drawing::Point(106, 338);
			this->m2updown->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 12, 0, 0, 0 });
			this->m2updown->Name = L"m2updown";
			this->m2updown->Size = System::Drawing::Size(38, 20);
			this->m2updown->TabIndex = 25;
			// 
			// m1updown
			// 
			this->m1updown->Location = System::Drawing::Point(106, 293);
			this->m1updown->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 12, 0, 0, 0 });
			this->m1updown->Name = L"m1updown";
			this->m1updown->Size = System::Drawing::Size(38, 20);
			this->m1updown->TabIndex = 24;
			// 
			// label9
			// 
			this->label9->AutoSize = true;
			this->label9->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label9->Location = System::Drawing::Point(18, 199);
			this->label9->Name = L"label9";
			this->label9->Size = System::Drawing::Size(38, 20);
			this->label9->TabIndex = 23;
			this->label9->Text = L"Title";
			// 
			// balanceText
			// 
			this->balanceText->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->balanceText->Location = System::Drawing::Point(93, 241);
			this->balanceText->Name = L"balanceText";
			this->balanceText->Size = System::Drawing::Size(107, 26);
			this->balanceText->TabIndex = 12;
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label2->Location = System::Drawing::Point(20, 244);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(67, 20);
			this->label2->TabIndex = 5;
			this->label2->Text = L"Balance";
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label1->Location = System::Drawing::Point(20, 161);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(124, 24);
			this->label1->TabIndex = 4;
			this->label1->Text = L"New Budget";
			// 
			// openButton
			// 
			this->openButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->openButton->Location = System::Drawing::Point(79, 60);
			this->openButton->Name = L"openButton";
			this->openButton->Size = System::Drawing::Size(108, 40);
			this->openButton->TabIndex = 3;
			this->openButton->Text = L"Open";
			this->openButton->UseVisualStyleBackColor = true;
			this->openButton->Click += gcnew System::EventHandler(this, &MainForm::openButton_Click);
			// 
			// label6
			// 
			this->label6->AutoSize = true;
			this->label6->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label6->Location = System::Drawing::Point(20, 293);
			this->label6->Name = L"label6";
			this->label6->Size = System::Drawing::Size(87, 20);
			this->label6->TabIndex = 21;
			this->label6->Text = L"Start Date:";
			// 
			// label4
			// 
			this->label4->AutoSize = true;
			this->label4->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label4->Location = System::Drawing::Point(146, 334);
			this->label4->Name = L"label4";
			this->label4->Size = System::Drawing::Size(15, 24);
			this->label4->TabIndex = 14;
			this->label4->Text = L"/";
			// 
			// label7
			// 
			this->label7->AutoSize = true;
			this->label7->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label7->Location = System::Drawing::Point(20, 337);
			this->label7->Name = L"label7";
			this->label7->Size = System::Drawing::Size(81, 20);
			this->label7->TabIndex = 22;
			this->label7->Text = L"End Date:";
			// 
			// label3
			// 
			this->label3->AutoSize = true;
			this->label3->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label3->Location = System::Drawing::Point(146, 289);
			this->label3->Name = L"label3";
			this->label3->Size = System::Drawing::Size(15, 24);
			this->label3->TabIndex = 13;
			this->label3->Text = L"/";
			// 
			// budgetLabel
			// 
			this->budgetLabel->AutoSize = true;
			this->budgetLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 27.75F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->budgetLabel->Location = System::Drawing::Point(468, 12);
			this->budgetLabel->Name = L"budgetLabel";
			this->budgetLabel->Size = System::Drawing::Size(137, 42);
			this->budgetLabel->TabIndex = 5;
			this->budgetLabel->Text = L"Budget";
			// 
			// purchaseGrid
			// 
			this->purchaseGrid->BackgroundColor = System::Drawing::SystemColors::ControlDark;
			this->purchaseGrid->BorderStyle = System::Windows::Forms::BorderStyle::Fixed3D;
			dataGridViewCellStyle5->Alignment = System::Windows::Forms::DataGridViewContentAlignment::MiddleLeft;
			dataGridViewCellStyle5->BackColor = System::Drawing::SystemColors::Control;
			dataGridViewCellStyle5->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 21.75F, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			dataGridViewCellStyle5->ForeColor = System::Drawing::SystemColors::WindowText;
			dataGridViewCellStyle5->SelectionBackColor = System::Drawing::SystemColors::Highlight;
			dataGridViewCellStyle5->SelectionForeColor = System::Drawing::SystemColors::HighlightText;
			dataGridViewCellStyle5->WrapMode = System::Windows::Forms::DataGridViewTriState::True;
			this->purchaseGrid->ColumnHeadersDefaultCellStyle = dataGridViewCellStyle5;
			this->purchaseGrid->ColumnHeadersHeightSizeMode = System::Windows::Forms::DataGridViewColumnHeadersHeightSizeMode::AutoSize;
			this->purchaseGrid->Columns->AddRange(gcnew cli::array< System::Windows::Forms::DataGridViewColumn^  >(2) {
				this->purchasedItem,
					this->itemPrice
			});
			this->purchaseGrid->Cursor = System::Windows::Forms::Cursors::Hand;
			dataGridViewCellStyle7->Alignment = System::Windows::Forms::DataGridViewContentAlignment::MiddleLeft;
			dataGridViewCellStyle7->BackColor = System::Drawing::SystemColors::Window;
			dataGridViewCellStyle7->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			dataGridViewCellStyle7->ForeColor = System::Drawing::SystemColors::ControlText;
			dataGridViewCellStyle7->SelectionBackColor = System::Drawing::SystemColors::Highlight;
			dataGridViewCellStyle7->SelectionForeColor = System::Drawing::SystemColors::HighlightText;
			dataGridViewCellStyle7->WrapMode = System::Windows::Forms::DataGridViewTriState::False;
			this->purchaseGrid->DefaultCellStyle = dataGridViewCellStyle7;
			this->purchaseGrid->Enabled = false;
			this->purchaseGrid->Location = System::Drawing::Point(286, 109);
			this->purchaseGrid->Name = L"purchaseGrid";
			dataGridViewCellStyle8->Alignment = System::Windows::Forms::DataGridViewContentAlignment::MiddleLeft;
			dataGridViewCellStyle8->BackColor = System::Drawing::SystemColors::Control;
			dataGridViewCellStyle8->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 15.75F, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			dataGridViewCellStyle8->ForeColor = System::Drawing::SystemColors::WindowText;
			dataGridViewCellStyle8->SelectionBackColor = System::Drawing::SystemColors::Highlight;
			dataGridViewCellStyle8->SelectionForeColor = System::Drawing::SystemColors::HighlightText;
			dataGridViewCellStyle8->WrapMode = System::Windows::Forms::DataGridViewTriState::True;
			this->purchaseGrid->RowHeadersDefaultCellStyle = dataGridViewCellStyle8;
			this->purchaseGrid->ScrollBars = System::Windows::Forms::ScrollBars::Vertical;
			this->purchaseGrid->Size = System::Drawing::Size(442, 312);
			this->purchaseGrid->TabIndex = 6;
			// 
			// purchasedItem
			// 
			this->purchasedItem->HeaderText = L"Purchased Item";
			this->purchasedItem->MinimumWidth = 250;
			this->purchasedItem->Name = L"purchasedItem";
			this->purchasedItem->Resizable = System::Windows::Forms::DataGridViewTriState::False;
			this->purchasedItem->Width = 250;
			// 
			// itemPrice
			// 
			dataGridViewCellStyle6->Format = L"C2";
			dataGridViewCellStyle6->NullValue = nullptr;
			this->itemPrice->DefaultCellStyle = dataGridViewCellStyle6;
			this->itemPrice->HeaderText = L"Price";
			this->itemPrice->MinimumWidth = 150;
			this->itemPrice->Name = L"itemPrice";
			this->itemPrice->Resizable = System::Windows::Forms::DataGridViewTriState::False;
			this->itemPrice->Width = 150;
			// 
			// updateButton
			// 
			this->updateButton->Enabled = false;
			this->updateButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->updateButton->Location = System::Drawing::Point(286, 427);
			this->updateButton->Name = L"updateButton";
			this->updateButton->Size = System::Drawing::Size(96, 36);
			this->updateButton->TabIndex = 8;
			this->updateButton->Text = L"Update";
			this->updateButton->UseVisualStyleBackColor = true;
			this->updateButton->Click += gcnew System::EventHandler(this, &MainForm::updateButton_Click);
			// 
			// label5
			// 
			this->label5->AutoSize = true;
			this->label5->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 15.75F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label5->Location = System::Drawing::Point(734, 109);
			this->label5->Name = L"label5";
			this->label5->Size = System::Drawing::Size(176, 25);
			this->label5->TabIndex = 9;
			this->label5->Text = L"Original Balance:";
			// 
			// curBalanceLabel
			// 
			this->curBalanceLabel->AutoSize = true;
			this->curBalanceLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 15.75F, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->curBalanceLabel->Location = System::Drawing::Point(734, 269);
			this->curBalanceLabel->Name = L"curBalanceLabel";
			this->curBalanceLabel->Size = System::Drawing::Size(173, 25);
			this->curBalanceLabel->TabIndex = 10;
			this->curBalanceLabel->Text = L"Current Balance:";
			// 
			// curBallance
			// 
			this->curBallance->AutoSize = true;
			this->curBallance->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 27.75F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->curBallance->Location = System::Drawing::Point(763, 299);
			this->curBallance->Name = L"curBallance";
			this->curBallance->Size = System::Drawing::Size(112, 42);
			this->curBallance->TabIndex = 11;
			this->curBallance->Text = L"$0.00";
			// 
			// origBalanceLabel
			// 
			this->origBalanceLabel->AutoSize = true;
			this->origBalanceLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 27.75F, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->origBalanceLabel->Location = System::Drawing::Point(763, 139);
			this->origBalanceLabel->Name = L"origBalanceLabel";
			this->origBalanceLabel->Size = System::Drawing::Size(112, 42);
			this->origBalanceLabel->TabIndex = 12;
			this->origBalanceLabel->Text = L"$0.00";
			// 
			// label8
			// 
			this->label8->AutoSize = true;
			this->label8->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 15.75F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label8->Location = System::Drawing::Point(734, 189);
			this->label8->Name = L"label8";
			this->label8->Size = System::Drawing::Size(163, 25);
			this->label8->TabIndex = 23;
			this->label8->Text = L"Weekly Budget:";
			// 
			// weeklyBudgetLabel
			// 
			this->weeklyBudgetLabel->AutoSize = true;
			this->weeklyBudgetLabel->BackColor = System::Drawing::Color::Transparent;
			this->weeklyBudgetLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 27.75F, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->weeklyBudgetLabel->Location = System::Drawing::Point(763, 219);
			this->weeklyBudgetLabel->Name = L"weeklyBudgetLabel";
			this->weeklyBudgetLabel->Size = System::Drawing::Size(112, 42);
			this->weeklyBudgetLabel->TabIndex = 24;
			this->weeklyBudgetLabel->Text = L"$0.00";
			// 
			// label11
			// 
			this->label11->AutoSize = true;
			this->label11->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 15.75F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label11->Location = System::Drawing::Point(734, 349);
			this->label11->Name = L"label11";
			this->label11->Size = System::Drawing::Size(153, 25);
			this->label11->TabIndex = 29;
			this->label11->Text = L"Budget Status:";
			// 
			// statusText
			// 
			this->statusText->AutoSize = true;
			this->statusText->BackColor = System::Drawing::Color::Transparent;
			this->statusText->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 27.75F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->statusText->ForeColor = System::Drawing::Color::Black;
			this->statusText->Location = System::Drawing::Point(763, 379);
			this->statusText->Name = L"statusText";
			this->statusText->Size = System::Drawing::Size(112, 42);
			this->statusText->TabIndex = 30;
			this->statusText->Text = L"$0.00";
			// 
			// label12
			// 
			this->label12->AutoSize = true;
			this->label12->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label12->Location = System::Drawing::Point(575, 427);
			this->label12->Margin = System::Windows::Forms::Padding(2, 0, 2, 0);
			this->label12->Name = L"label12";
			this->label12->Size = System::Drawing::Size(81, 20);
			this->label12->TabIndex = 31;
			this->label12->Text = L"End Date:";
			// 
			// endDateLabel
			// 
			this->endDateLabel->AutoSize = true;
			this->endDateLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->endDateLabel->Location = System::Drawing::Point(660, 427);
			this->endDateLabel->Margin = System::Windows::Forms::Padding(2, 0, 2, 0);
			this->endDateLabel->Name = L"endDateLabel";
			this->endDateLabel->Size = System::Drawing::Size(31, 20);
			this->endDateLabel->TabIndex = 32;
			this->endDateLabel->Text = L"0/0";
			// 
			// startDateLabel
			// 
			this->startDateLabel->AutoSize = true;
			this->startDateLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->startDateLabel->Location = System::Drawing::Point(493, 427);
			this->startDateLabel->Margin = System::Windows::Forms::Padding(2, 0, 2, 0);
			this->startDateLabel->Name = L"startDateLabel";
			this->startDateLabel->Size = System::Drawing::Size(31, 20);
			this->startDateLabel->TabIndex = 33;
			this->startDateLabel->Text = L"0/0";
			// 
			// label15
			// 
			this->label15->AutoSize = true;
			this->label15->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label15->Location = System::Drawing::Point(398, 427);
			this->label15->Margin = System::Windows::Forms::Padding(2, 0, 2, 0);
			this->label15->Name = L"label15";
			this->label15->Size = System::Drawing::Size(91, 20);
			this->label15->TabIndex = 34;
			this->label15->Text = L"Start Date: ";
			// 
			// curTimeLabel
			// 
			this->curTimeLabel->AutoSize = true;
			this->curTimeLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 24, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->curTimeLabel->Location = System::Drawing::Point(279, 54);
			this->curTimeLabel->Name = L"curTimeLabel";
			this->curTimeLabel->Size = System::Drawing::Size(62, 37);
			this->curTimeLabel->TabIndex = 35;
			this->curTimeLabel->Text = L"0/0";
			// 
			// MainForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->AutoSizeMode = System::Windows::Forms::AutoSizeMode::GrowAndShrink;
			this->BackColor = System::Drawing::Color::White;
			this->ClientSize = System::Drawing::Size(953, 526);
			this->Controls->Add(this->curTimeLabel);
			this->Controls->Add(this->label15);
			this->Controls->Add(this->startDateLabel);
			this->Controls->Add(this->endDateLabel);
			this->Controls->Add(this->label12);
			this->Controls->Add(this->statusText);
			this->Controls->Add(this->label11);
			this->Controls->Add(this->weeklyBudgetLabel);
			this->Controls->Add(this->label8);
			this->Controls->Add(this->origBalanceLabel);
			this->Controls->Add(this->curBallance);
			this->Controls->Add(this->curBalanceLabel);
			this->Controls->Add(this->label5);
			this->Controls->Add(this->updateButton);
			this->Controls->Add(this->purchaseGrid);
			this->Controls->Add(this->budgetLabel);
			this->Controls->Add(this->panel1);
			this->MaximumSize = System::Drawing::Size(969, 565);
			this->MinimumSize = System::Drawing::Size(969, 565);
			this->Name = L"MainForm";
			this->Text = L"MainForm";
			this->panel1->ResumeLayout(false);
			this->panel1->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->y2updown))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->y1updown))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->d2updown))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->d1updown))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->m2updown))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->m1updown))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->purchaseGrid))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	
	/*
	******************************************************************************************************************************************
	*
	*Performs the actions when the ADD button is clicked
	*
	******************************************************************************************************************************************
	*/
	private: System::Void newBudgetButton_Click(System::Object^  sender, System::EventArgs^  e) {
		if (!checkForDouble(balanceText->Text)) {//makes sure the balance enetered is a valid double
			MessageBox::Show("One (or more price) entered is invalid.", "ERROR", MessageBoxButtons::OK);
		}
		System::String^ temp = newBudgetText->Text->Trim();
		temp = temp->Trim();
		if(temp->IndexOf(' ') >= 0 || temp->IndexOf('/') >= 0) {// makes sure the title does not have a space in it
			MessageBox::Show("The title of your budget can not contain the \"/\" or \"Space\" char.", "ERROR", MessageBoxButtons::OK);
		}
		if (newBudgetText->Text->IndexOf(' ') <= 0 && checkForDouble(balanceText->Text)&& m1updown->Text != "0" &&
			d1updown->Text != "0" && m2updown->Text != "0" && d2updown->Text != "0"&& y1updown->Text != "0" && y2updown->Text != "0") {// if all the parameters are filled out correctly  
			std::ofstream outfile("budgets.txt", std::ios_base::app | std::ios_base::out);
			if (outfile.is_open()) {
				std::string stdStr;
				msclr::interop::marshal_context context;
				
				// formats string that will be sent to budgets.txt
				if(balanceText->Text->IndexOf('$')>=0)
					stdStr = context.marshal_as<std::string>(newBudgetText->Text +": $"+ balanceText->Text->Substring(1) + 
						"*" + m1updown->Text + "/" + d1updown->Text + "/"+y1updown->Text + "/" + m2updown->Text + "/" + d2updown->Text + "/" + y2updown->Text);
				else
					stdStr = context.marshal_as<std::string>(newBudgetText->Text + ": $" + balanceText->Text+
						"*" + m1updown->Text + "/" + d1updown->Text + "/" + y1updown->Text + "/" + m2updown->Text + "/" + d2updown->Text + "/" + y2updown->Text);
				outfile << stdStr << std::endl;// writes to file budgets.txt

				// updates dropdown
				budgetDropdown->Items->Clear();
				getBudgets();

				//resets all parameter
				newBudgetText->Text = "";
				balanceText->Text = "";
				m1updown->Text = "0";
				m2updown->Text = "0";
				d1updown->Text = "0";
				d2updown->Text = "0";
				y1updown->Text = "0";
				y2updown->Text = "0";
				outfile.close();
			}// end if
		}// end if
	}// end onClick

	/*
	******************************************************************************************************************************************
	*
	*Performs the actions when the OPEN button is clicked
	*
	******************************************************************************************************************************************
	*/
	private: System::Void openButton_Click(System::Object^  sender, System::EventArgs^  e) {
		if (budgetDropdown->Text != "Select Budget") {
			purchaseGrid->Rows->Clear();
			getDates();
			getBudgetHistory();
			getCurrentBallance();
			getWeeklyBudget();
			getBudgetStatus(); 
			updateButton->Enabled = true;
			purchaseGrid->Enabled = true;
		}
	}// end onClick

	/*
	******************************************************************************************************************************************
	*
	*Performs the actions when the UPDATE button is clicked
	*
	******************************************************************************************************************************************
	*/
	private: System::Void updateButton_Click(System::Object^  sender, System::EventArgs^  e) {
		bool run = true;
		for(int i = 0; i < purchaseGrid->Rows->Count - 1; i++) {
			if (purchaseGrid->Rows[i]->Cells[1]->Value != nullptr) {
				if (!checkForDouble(purchaseGrid->Rows[i]->Cells[1]->Value->ToString())) {
					run = false;
				}
				if (purchaseGrid->Rows[i]->Cells[1]->Value->ToString()->Length < 1) {
					run = false;
				}
			}
		}
	
		if (run) {
			updateBudgetHistory();
			purchaseGrid->Rows->Clear();
			getBudgetHistory();
			getCurrentBallance();
			getWeeklyBudget();
			getBudgetStatus();
		}
		else {
			MessageBox::Show("One (or more price) entered is invalid.", "ERROR", MessageBoxButtons::OK);
		}
}// end onClick



};// end MainForm

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////MainForm Fuctions////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	******************************************************************************************************************************************
	*
	*gets the budgets from a file and places them in a combo menu
	*
	******************************************************************************************************************************************
	*/
	void MainForm::getBudgets() {
	   
		std::ifstream infile;
		infile.open("budgets.txt"); // where te list of budgets is stored
				
		if (infile.is_open()) {
			std::string str;
			// places listed budgets in the dropdown menu
			while (std::getline(infile, str)) {
				
				System::String^ line = gcnew String(str.c_str());
				if(line->IndexOf('*') > 0)
				line = line->Substring(0, line->IndexOf('*')); // only gets the title and ogBalance
				if(line->Length > 0)
					budgetDropdown->Items->Add(line);
			}
		}
		infile.close();
	}// end getBudgets

	/*
	******************************************************************************************************************************************
	*
	*reads from files to get the history of budget being opened
	*
	******************************************************************************************************************************************
	*/
	void MainForm::getBudgetHistory() {
		std::ifstream infile;

		std::string filename;
		msclr::interop::marshal_context context;
		filename = context.marshal_as<std::string>(budgetDropdown->Text);// gets selected budget
		filename = filename.substr(0,filename.find(':')); //cuts it down to just the title

		infile.open(filename + ".txt");
		if (infile.is_open()) {
			std::string str;
			while (std::getline(infile, str)) {// separates the strings and then places them in two different columns
				int index = str.find('$');
				std::string tempPurch = str.substr(0,index);// the item purchased
				std::string tempPrice = str.substr(index);// the price it was purchased at
				purchaseGrid->Rows->Add(gcnew String(tempPurch.c_str()), gcnew String(tempPrice.c_str()));// adds the row and data
			}
		}
		System::String^ oBalance;
		oBalance = budgetDropdown->Text;
		int temp = oBalance->IndexOf('$');
		oBalance = oBalance->Substring(budgetDropdown->Text->IndexOf('$'));// gets the starting balance of the budget
		origBalanceLabel->Text = oBalance;
		budgetLabel->Text = budgetDropdown->Text->Substring(0,budgetDropdown->Text->IndexOf(':'));// sets the title to the title of the budget

		infile.close();
	}// end getBudgetHistory
	
	/*
	******************************************************************************************************************************************
	*
	*updates the text file with the data
	*
	******************************************************************************************************************************************
	*/
	void MainForm::updateBudgetHistory() {
		std::ofstream outfile;
		std::string filename;
		msclr::interop::marshal_context context;
		filename = context.marshal_as<std::string>(budgetDropdown->Text);
		filename = filename.substr(0, filename.find(':')); //cuts it down to just the title

		outfile.open(filename + ".txt");
		if (outfile.is_open()) {
			for (int i = 0; i < purchaseGrid->Rows->Count - 1; i++) {// goes through the data and writes it to a file
				std::string price;
				std::string item;
				if (purchaseGrid->Rows[i]->Cells[0]->Value != nullptr && purchaseGrid->Rows[i]->Cells[1]->Value != nullptr 
					&& purchaseGrid->Rows[i]->Cells[1]->Value->ToString()->Length > 0 ) {
					price = msclr::interop::marshal_as<std::string>(purchaseGrid->Rows[i]->Cells[1]->Value->ToString());// gets the price
					item = msclr::interop::marshal_as<std::string>(purchaseGrid->Rows[i]->Cells[0]->Value->ToString());// gets the item
					if (price.find('$') == -1) {
						price = "$" + price;
					}
					
					outfile << item + price << std::endl;// writes data to file
				}// end if
				
			}// end for()
		}//end if()
		outfile.close();
	}// end updateBudgetHistory
	
	/*
	******************************************************************************************************************************************
	*
	*Looks through the price column and returns the amount of money spent.
	*
	******************************************************************************************************************************************
	*/
	double MainForm::trackSpending() {
		double spending = 0.00;
		System::String^ price;
		double tempPrice;
		for (int i = 0; i < purchaseGrid->Rows->Count - 1; i++) {// goes through prices listed and comes up with a total
			if (purchaseGrid->Rows[i]->Cells[1]->Value != nullptr) {
				price = purchaseGrid->Rows[i]->Cells[1]->Value->ToString();
				price = price->Substring(1);
				tempPrice = System::Convert::ToDouble(price);
				spending += tempPrice;// adding up the total 
			}
		}
		return spending;
	}// end trackSpending

	/*
	******************************************************************************************************************************************
	*
	*uses the original balance and calculates how much money is left.
	*
	******************************************************************************************************************************************
	*/
	void MainForm::getCurrentBallance() {
		System::String^ balanceString;
		balanceString = origBalanceLabel->Text; // gets the start balance
		double oBalance;

		if(balanceString->IndexOf('$') >= 0)
		balanceString = balanceString->Substring(1);

		oBalance = System::Convert::ToDouble(balanceString);
		
		double cBalance = oBalance - trackSpending();// gets the current ballance
		double scale = 0.01;
		cBalance  = floor(cBalance / scale + 0.5) * scale;
		System::String^ cBalanceString = "$" + cBalance;
		curBallance->Text = cBalanceString;
		if (cBalance < 0) { // if the balance is in the negatives
			curBallance->ForeColor = System::Drawing::Color::Red;
		}
		else // if it is not in the negatives
			curBallance->ForeColor = System::Drawing::Color::Green;

		globalBalance = oBalance;
		globalCurBalance = cBalance;
	}// end getCurrentBallance

	/*
	******************************************************************************************************************************************
	*
	*takes two dates within the same year and gets the number of days in between te dates
	*
	******************************************************************************************************************************************
	*/
	int MainForm::getDaysBetween(int m1, int d1,int y1, int m2, int d2, int y2) {
		int daysInMonth[12] = { 31,28,31,30,31,30,31,31,30,31,30,31 };
		if (y1 < y2) { // if the two dates spanes over the course of 2 different years 
			int dayTot = 0;
			dayTot += (daysInMonth[m1 - 1] - d1);// adds the days remaining days in start month
			int tempDays = 0;
			for (int i = m1; i < 12; i++) {// adds rest of days in the year 
				dayTot += daysInMonth[i];
			}
			y1++;// increments year
			while (y1 < y2) {// whole year // could just add 365... :/
				for (int i = 0; i < 12; i++) {
					dayTot += daysInMonth[i];
				}
				y1++;// increments years 
			}

			for (int i = 0; i < m2 - 1; i++) {// adds remaining days in the last year 
				dayTot += daysInMonth[i];
			}
			dayTot += d2;

			return dayTot;// return amound of days

		}
		else {// if both dates fall within the same year 
			int days1 = 0;// the day number from start date
			for (int i = 0; i < m1; i++) {// adds days up until last month
				days1 += daysInMonth[i];
			}
			days1 += d1;// adds remaining days 

			int days2 = 0;// the day number from the end date 
			for (int i = 0; i < m2; i++) {// adds days upto last manth 
				days2 += daysInMonth[i];
			}
			days2 += d2;// adds remaining days 

			int dayGap = days2 - days1;// gets the difference 


			return dayGap;// return total number of days 
		}
	}// end getDaysBetween

	/*
	******************************************************************************************************************************************
	*
	*Takes the start and ewnd dates if teh overal budget and spits out a weekly budget
	*
	******************************************************************************************************************************************
	*/
	void MainForm::getWeeklyBudget() {
			int dayGap = getDaysBetween(m1, d1, y1, m2, d2, y2);

			double dailyBudg = globalBalance / dayGap;
			double weeklyBudg = dailyBudg * 7;
			double scale = 0.01;  // i.e. round to nearest one-hundreth
			weeklyBudg = floor(weeklyBudg / scale + 0.5) * scale;

			weeklyBudgetLabel->Text = "$" + weeklyBudg;
	}// end getWeeklyBudget

	/*
	******************************************************************************************************************************************
	*
	* takes dates and gives used feedback on where they stand in terms of their budget
	*
	******************************************************************************************************************************************
	*/
	void MainForm::getBudgetStatus() {
			int totDayGap = getDaysBetween(m1, d1, y1, m2, d2, y2);
			double dailyBudg = globalBalance / totDayGap;
			int curDayGap;
		
			curDayGap = getDaysBetween(m1, d1, y1, cm, cd, cy);

			double projBal = globalBalance - (dailyBudg*curDayGap);// how much should be left as of the current day
			double scale = 0.01;  // i.e. round to nearest one-hundreth
			projBal = floor(projBal / scale + 0.5) * scale;

			double statusBal = globalCurBalance - projBal;// the difference between the current ballance and the projected ballance
			statusBal = floor(statusBal / scale + 0.5)* scale;

			if (statusBal <= 0) // if the diference is 0 or below 
				statusText->ForeColor = System::Drawing::Color::Red;
			
			else// if the difference is more than 0
				statusText->ForeColor = System::Drawing::Color::Green;

			if (cm > m2 && cd > d2) {
				statusText->ForeColor = System::Drawing::Color::Blue;
				statusText->Text = "Budget\nClosed";
			}
			else

			statusText->Text = "$"+statusBal;
	} // end getBudgetStatus

	/*
	******************************************************************************************************************************************
	*
	*reads the dates from the budget list and places them in the propper labels;
	*
	******************************************************************************************************************************************
	*/
	void MainForm::getDates() {
		System::String^ neededLine;

		std::ifstream infile;
		infile.open("budgets.txt");

		if (infile.is_open()) {
			std::string str;
			while (std::getline(infile, str)) {// searches for selected budget
				System::String^ line = gcnew String(str.c_str());
				System::String^ newLine;
				if (line->IndexOf(':') > 0) {
					newLine = line->Substring(0, line->IndexOf(':'));
				}
				System::String^ target = budgetDropdown->Text->Substring(0, budgetDropdown->Text->IndexOf(':'));
				if (newLine->Equals(target)) {// if selected budget is found
					neededLine = line;
					break;
				}
			}// end while()
			if (neededLine->IndexOf('*') > 0)
				neededLine = neededLine->Substring(neededLine->IndexOf('*'));// cuts the string down to just the dates

			std::string stdneeded = msclr::interop::marshal_as<std::string>(neededLine);
			std::smatch m;
			std::regex e("[0-9]+");// search for integers
			std::sregex_iterator iter(stdneeded.begin(), stdneeded.end(), e);
			std::sregex_iterator end;
			std::string values[6];// array of integers placed in a specific order 
			
			int index = 0;
			while (iter != end)// finds integers an places them in array
			{
				for (unsigned i = 0; i < iter->size(); ++i)
				{
					values[index] = (*iter)[i];// places integer
				}
				++iter;
				index++;
			}// end while()

			// assign date values
			m1 = std::stoi(values[0]);
			d1 = std::stoi(values[1]);
			m2 = std::stoi(values[3]);
			d2 = std::stoi(values[4]);
			y1 = std::stoi(values[2])+ 2000;
			y2 = std::stoi(values[5])+ 2000;

			//places dates on the GUI
			System::String^ startdate = m1 + "/" + d1 + "/" + y1;
			System::String^ enddate = m2 + "/" + d2 + "/" + y2;

			startDateLabel->Text = startdate;
			endDateLabel->Text = enddate;

			infile.close();
		}
	}// end getDates

	/*
	******************************************************************************************************************************************
	*
	*checks a string and makes sure it is able to be converted into a double 
	*
	******************************************************************************************************************************************
	*/
	bool MainForm::checkForDouble(System::String^ number) {
		number = number->Trim();
		if (number->IndexOf('$') >= 0) {// accounts for possible dollar sign
			number = number->Substring(1);
		}

		int point = 0;// make sure there is only one decial point

		for(int i = 0; i < number->Length; i++) {// loops through all chars 
			char c = number[i];
			if(!isdigit(c)){// detrmines if the char is a didget
				if (c != '.' && c != '-')// if it isnt a didget or decimal point
					return false; //doube found
				else
					point++; // track number of decimal points
			}
			
		}

		if (point > 1)// if too any decimal points
			return false; // double not found 

		return true;// double found 
	}// end checkForDouble

 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////////////////////////////////////////////////////////End MainForm Fuctions////////////////////////////////////////////////////////////////////////////////////
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 } // end namespace