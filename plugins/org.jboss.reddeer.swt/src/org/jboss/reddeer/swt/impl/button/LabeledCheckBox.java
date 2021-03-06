/******************************************************************************* 
 * Copyright (c) 2016 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.reddeer.swt.impl.button;

import org.jboss.reddeer.core.matcher.WithLabelMatcher;
import org.jboss.reddeer.core.reference.ReferencedComposite;

/**
 * Labeled Check Box implementation. Do not replace label with text.
 * 
 * @author apodhrad
 *
 */
public class LabeledCheckBox extends CheckBox {

	/**
	 * Finds a check box with a given label.
	 * 
	 * @param label the label
	 */
	public LabeledCheckBox(String label) {
		this(label, 0);
	}

	/**
	 * Finds a check box with a given label at the specified index.
	 * 
	 * @param label the label
	 * @param index the index
	 */
	public LabeledCheckBox(String label, int index) {
		this(null, label, index);
	}

	/**
	 * Finds a check box with a given label inside a given reference.
	 * 
	 * @param ref the reference
	 * @param label the label
	 */
	public LabeledCheckBox(ReferencedComposite ref, String label) {
		this(ref, label, 0);
	}

	/**
	 * Finds a check box with a given label inside a given reference at the specified index.
	 * 
	 * @param ref the reference
	 * @param label the label
	 * @param index the index
	 */
	public LabeledCheckBox(ReferencedComposite ref, String label, int index) {
		super(ref, index, new WithLabelMatcher(label));
	}
}
