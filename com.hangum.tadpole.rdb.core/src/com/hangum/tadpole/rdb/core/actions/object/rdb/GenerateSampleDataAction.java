/*******************************************************************************
 * Copyright (c) 2013 hangum.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     hangum - initial API and implementation
 ******************************************************************************/
package com.hangum.tadpole.rdb.core.actions.object.rdb;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import com.hangum.tadpold.commons.libs.core.define.PublicTadpoleDefine;
import com.hangum.tadpole.dao.mysql.TableDAO;
import com.hangum.tadpole.rdb.core.actions.object.AbstractObjectAction;
import com.hangum.tadpole.rdb.core.ext.sampledata.SampleDataGenerateDialog;
import com.hangum.tadpole.rdb.core.viewers.object.ExplorerViewer;

/**
 * Object Explorer에서 사용하는 공통 action
 * 
 * @author hangum
 *
 */
public class GenerateSampleDataAction extends AbstractObjectAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GenerateSampleDataAction.class);

	public final static String ID = "com.hangum.db.browser.rap.core.actions.object.generatesample.data";
	
	public GenerateSampleDataAction(IWorkbenchWindow window, PublicTadpoleDefine.DB_ACTION actionType, String title) {
		super(window, actionType);
		setId(ID + actionType.toString());
		setText("Generate Sample data");
		
//		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void run() {
		TableDAO tableDao = (TableDAO)sel.getFirstElement();
		
		SampleDataGenerateDialog dialog = new SampleDataGenerateDialog(window.getShell(), userDB, tableDao.getName());
		dialog.open();
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		this.sel = (IStructuredSelection)selection;
	
		if(ExplorerViewer.ID.equals( part.getSite().getId() )) {			
//			if(userDB != null) {
				if(selection instanceof IStructuredSelection && !selection.isEmpty()) {
					setEnabled(this.sel.size() > 0);
				} else setEnabled(false);
//			}
//			else setEnabled(false);
		} else {
			setEnabled(false);
		}
	}
}
