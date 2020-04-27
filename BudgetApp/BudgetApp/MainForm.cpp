#include "MainForm.h"
#include <Windows.h>
#include <sstream>

using namespace BudgetApp; // This is your project name

using namespace System;
using namespace System::Windows::Forms;

[STAThread]
int main()
{
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false);
	BudgetApp::MainForm form;
	Application::Run(%form);
	return 0;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////